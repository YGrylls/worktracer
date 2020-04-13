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

    @Select("SELECT uid, companyId, percentFix, checkIn, checkOut FROM daily_check ORDER BY checkIn LIMIT #{page}, #{size}")
    List<CheckInOut> getAllCheckRecord(int page, int size);

}
