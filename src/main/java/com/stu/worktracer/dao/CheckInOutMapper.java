package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CheckInOutMapper {

    @Insert("INSERT INTO daily_check (uid, companyId, percentFix, checkIn) VALUES (#{uid}, #{companyId}, #{percentFix}, #{checkIn})")
    int addCheckRecord(CheckInOut checkInOut);

    @Update("UPDATE daily_check SET checkOut = #{checkOut} WHERE uid = #{uid}")
    int updateCheckOut(Long uid, Date checkOut);

    @Delete("DELETE FROM daily_check")
    int clearAll();

    @Select("SELECT uid, daily_check.companyId, percentFix, checkIn, checkOut, company.name AS companyName FROM daily_check JOIN company ON daily_check.companyId = company.companyId WHERE daily_check.checkOut IS NOT NULL ORDER BY checkIn LIMIT #{page}, #{size}")
    List<CheckInOut> getAllCheckRecord(int page, int size);

    @Select("SELECT uid, companyId, percentFix, checkIn, checkOut FROM daily_check WHERE daily_check.uid = #{uid}")
    CheckInOut getCheckRecordByUid(Long uid);

}
