package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ToApproveMapper {

    @Insert("INSERT INTO to_approve (companyName, workshop, submitTime) VALUES (#{companyName}, #{workshop}, #{submitTime})")
    int addToApprove(ToApprove toApprove);

    @Delete("DELETE FROM to_approve WHERE id = #{id}")
    int deleteToApprove(Long id);

    @Select("SELECT companyName, workshop, submitTime FROM to_approve WHERE to_approve.companyName = #{companyName}")
    ToApprove getToApproveByName(String companyName);

}
