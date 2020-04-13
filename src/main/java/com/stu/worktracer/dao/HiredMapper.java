package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HiredMapper {

    @Insert("INSERT INTO hired (uid, companyId, modTime) VALUES (#{uid}, #{companyId}, #{modTime})")
    int addHired(Hired hired);

    @Update("UPDATE hired SET companyId = #{companyId}, modTime = #{modTime} WHERE uid = #{uid}")
    int updateHired(Hired hired);

    @Select("SELECT uid, companyId, modTime FROM hired WHERE hired.uid = #{uid}")
    Hired getHiredByUid(Long uid);

}
