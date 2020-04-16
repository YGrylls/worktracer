package com.stu.worktracer.service;

import com.stu.worktracer.dao.Company;
import com.stu.worktracer.dao.CompanyMapper;
import com.stu.worktracer.dto.DetailCompany;
import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.WelfareRecord;
import com.stu.worktracer.redis.RedisService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RatingService implements RatingServiceInterface {

    private final CompanyMapper companyMapper;

    private final ESService esService;

    private final RedisService redisService;

    public RatingService(CompanyMapper companyMapper, ESService esService, RedisService redisService) {
        this.companyMapper = companyMapper;
        this.esService = esService;
        this.redisService = redisService;
    }

    private final static int QUERY_SIZE = 100;

    //triggered or monthly scheduled
    @Override
    public synchronized void refreshAllRatings() {
        for (int page = 0; true; page++) {
            List<Company> companyList = companyMapper.getAllCompany(page, QUERY_SIZE);
            if (companyList == null || companyList.size() == 0) {
                break;
            }
            for (Company company : companyList) {
                Long companyId = company.getCompanyId();
                getRating(companyId);
            }
            if (companyList.size() < QUERY_SIZE) {
                break;
            }
        }
    }

    @Override
    public DetailCompany getRating(Long companyId) {
        DetailCompany cache = redisService.getCompanyCache(companyId);
        if (cache != null) {
            return cache;
        }
        DetailCompany dc = getTiming(companyId);
        dc.setWelfare(getWelfare(companyId));
        dc.setTotalRate(calRate(dc.getWorkOvertime(), dc.getWelfare()));
        dc.setCompanyId(companyId);
        //set cache
        companyMapper.updateRating(companyId, dc.getTotalRate(), dc.getWelfare());
        //todo disable cache for debug use
        redisService.setCompanyCache(dc);
        return dc;
    }

    private static final long STANDARD_WORK_TIME = 8 * 3600 * 1000L;
    // half hour ALW
    private static final long OVER_WORK_ALW = 1800 * 1000L;

    public DetailCompany getTiming(Long companyId) {
        List<CheckRecord> checkList = esService.queryCheckRecordMonth(companyId);
        double totalNumMonth = 0;
        double totalInMonth = 0;
        double totalOutMonth = 0;
        double totalNumWeek = 0;
        double totalInWeek = 0;
        double totalOutWeek = 0;
        double workOvertime = 0;
        for (CheckRecord record : checkList) {
            long checkIn = record.getCheckInTime();
            long checkOut = record.getCheckOutTime();

            //get 0:00 of the day when check in
            long zeroHour = getZeroHour(checkIn).getTime();
            //check time of the day when check in
            long checkInDay = checkIn - zeroHour;
            long checkOutDay = checkOut - zeroHour;
            // percent fix
            double percent = (double) record.getPercentFix() / 100;

            //workOvertime
            long workTime = checkOut - checkIn;
            if (workTime <= 0) {
                //data invalid
                continue;
            }
            long overTime = workTime - STANDARD_WORK_TIME;
            if (overTime > OVER_WORK_ALW) {
                double overPercent = (double) overTime / STANDARD_WORK_TIME;
                if (overPercent > 1) {
                    overPercent = 1;
                }
                workOvertime += overPercent * percent;
            }


            //month
            totalInMonth += checkInDay * percent;
            totalOutMonth += checkOutDay * percent;
            totalNumMonth += percent;

            //week
            long week = getZeroHour(new Date().getTime()).getTime() - 3600 * 24 * 7 * 1000L;
            if (checkIn > week) {
                totalInWeek += checkInDay * percent;
                totalOutWeek += checkOutDay * percent;
                totalNumWeek += percent;
            }

        }
        DetailCompany dc = new DetailCompany();
        //week
        if (totalNumWeek <= 0) {
            dc.setStartWorkTimeWeek(0L);
            dc.setOffWorkTimeWeek(0L);
        } else {
            dc.setStartWorkTimeWeek((long) (totalInWeek / totalNumWeek));
            dc.setOffWorkTimeWeek((long) (totalOutWeek / totalNumWeek));
        }

        //month
        if (totalNumMonth <= 0) {
            dc.setStartWorkTimeMonth(0L);
            dc.setOffWorkTimeMonth(0L);
            dc.setWorkOvertime(0);
        } else {
            dc.setStartWorkTimeMonth((long) (totalInMonth / totalNumMonth));
            dc.setOffWorkTimeMonth((long) (totalOutMonth / totalNumMonth));
            dc.setWorkOvertime((int) (workOvertime * 100 / totalNumMonth));
        }


        return dc;
    }

    public Integer getWelfare(Long companyId) {
        List<WelfareRecord> esList = esService.queryWelfareThisMonth(companyId);
        double totalW = 0;
        double totalN = 0;
        for (WelfareRecord record : esList) {
            double percent = (double) record.getPercentFix() / 100;
            int welfare = record.getWelfare();
            totalW += welfare * percent;
            totalN += percent;
        }
        if (totalN <= 0) {
            return 0;
        }
        return (int) (totalW / totalN * 10);
    }

    //workOvertime - [0,100] ; welfare - [0,100]
    public int calRate(int workOvertime, int welfare) {
        double welfareFix = (double) welfare * welfare / 100; // 0 - 100
        double overTimeFix = 100 - 10 * Math.sqrt(workOvertime); // 10 - 100
        if (overTimeFix <= 10) {
            overTimeFix = 10;
        }
        double doubleRate = welfareFix * overTimeFix / 100;
        return (int) doubleRate;
    }

    private Date getZeroHour(long time) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(new Date(time));
        cld.set(Calendar.HOUR, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        return cld.getTime();
    }
}
