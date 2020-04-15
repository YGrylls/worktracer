package com.stu.worktracer.service;

import com.stu.worktracer.dao.*;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.WelfareRecord;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckService implements CheckServiceInterface {
    private final CheckInOutMapper checkInOutMapper;

    private final HiredMapper hiredMapper;

    private final UserMapper userMapper;

    private final ESService esService;

    private final CompanyMapper companyMapper;

    public CheckService(CheckInOutMapper checkInOutMapper, HiredMapper hiredMapper, UserMapper userMapper, ESService esService, CompanyMapper companyMapper) {
        this.checkInOutMapper = checkInOutMapper;
        this.hiredMapper = hiredMapper;
        this.userMapper = userMapper;
        this.esService = esService;
        this.companyMapper = companyMapper;
    }

    @Override
    public int checkCheck(Long uid) throws KnownException {
        CheckInOut check = checkInOutMapper.getCheckRecordByUid(uid);
        if (check == null) {
            return 0;
        }
        if (check.getCheckOut() == null) {
            return 1;
        }
        return 2;
    }

    @Override
    public void checkIn(Long uid) throws KnownException {
        CheckInOut exist = checkInOutMapper.getCheckRecordByUid(uid);
        if (exist != null) {
            throw new KnownException(ErrCode.CHECK_IN_OUT_ERROR);
        }
        Hired hired = hiredMapper.getHiredByUid(uid);
        if (hired == null) {
            throw new KnownException(ErrCode.CHECK_IN_OUT_ERROR);
        }
        CheckInOut check = new CheckInOut();
        check.setUid(hired.getUid());
        check.setCompanyId(hired.getCompanyId());
        check.setCheckIn(new Date());
        check.setPercentFix(getPercentFix(hired));
        checkInOutMapper.addCheckRecord(check);
    }

    @Override
    public void checkOut(Long uid) throws KnownException {
        CheckInOut check = checkInOutMapper.getCheckRecordByUid(uid);
        if (check == null) {
            throw new KnownException(ErrCode.CHECK_IN_OUT_ERROR);
        }
        checkInOutMapper.updateCheckOut(uid, new Date());
    }

    @Override
    @Transactional
    public void survey(Long uid, Integer welfare) throws KnownException {
        if (welfare < 0 || welfare > 10) {
            throw new KnownException(ErrCode.REQUEST_FORMAT_ERROR);
        }
        if (checkSurvey(uid)) {
            Hired hired = hiredMapper.getHiredByUid(uid);
            if (hired == null) {
                throw new KnownException(ErrCode.INTERNAL_SERVER_ERROR);
            }
            Company company = companyMapper.getCompanyById(hired.getCompanyId());
            WelfareRecord welfareRecord = new WelfareRecord();
            welfareRecord.setCompanyId(hired.getCompanyId());
            welfareRecord.setCompanyName(company.getName());
            welfareRecord.setSurveyTime(new Date().getTime());
            welfareRecord.setWelfare(welfare);
            welfareRecord.setUid(uid);
            welfareRecord.setPercentFix(getPercentFix(hired));
            esService.indexWelfare(welfareRecord);
        } else {
            throw new KnownException(ErrCode.REPEAT_MONTHLY_SURVEY_ERROR);
        }
    }

    @Override
    public Boolean checkSurvey(Long uid) throws KnownException {
        User user = userMapper.getUserSurveyByUid(uid);
        if (user == null) {
            throw new KnownException(ErrCode.INTERNAL_SERVER_ERROR);
        }
        return user.getSurveyed() != 1;

    }


    private final static int QUERY_SIZE = 100;

    @Override
    synchronized public void processDailyRecord() throws KnownException {
        for (int page = 0; true; page++) {
            List<CheckInOut> checkList = checkInOutMapper.getAllCheckRecord(page, QUERY_SIZE);
            if (checkList.size() == 0) {
                break;
            }
            List<CheckRecord> esList = new ArrayList<>(checkList.size());
            for (CheckInOut check : checkList) {
                if (check.getCheckOut() == null) {
                    continue;
                }
                CheckRecord record = new CheckRecord();
                record.setUid(check.getUid());
                record.setCompanyId(check.getCompanyId());
                record.setCompanyName(check.getCompanyName());
                record.setPercentFix(check.getPercentFix());
                record.setCheckInTime(check.getCheckIn().getTime());
                record.setCheckOutTime(check.getCheckOut().getTime());
                esList.add(record);
            }
            esService.indexCheckRecordBulk(esList);
            if (checkList.size() < QUERY_SIZE) {
                break;
            }
        }
        checkInOutMapper.clearAll();
    }


    //half year secs
    private static final long PERCENT_GATE = 30 * 6L;
    private final Sigmoid sigmoid = new Sigmoid(0, 1);
    private static final double NORM_RATE = 2.5;

    private int getPercentFix(Hired hired) {
        long modTime = hired.getModTime().getTime() / 1000 / 3600 / 24;
        long now = new Date().getTime() / 1000 / 3600 / 24;
        double minus = now - modTime;
        if (minus < 0) {
            minus = 0;
        }
        if (minus >= PERCENT_GATE) {
            return 100;
        }
        double x = minus / PERCENT_GATE * NORM_RATE;
        return (int) Math.round(sigmoid.value(x) * 100);

    }
}
