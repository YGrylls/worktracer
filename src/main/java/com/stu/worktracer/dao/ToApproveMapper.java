package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ToApproveMapper {

    @Insert("INSERT INTO to_approve (companyName, workshop, submitTime) VALUES (#{companyName}, #{workshop}, #{submitTime})")
    int addToApprove(ToApprove toApprove);

    @Delete("DELETE FROM to_approve WHERE id = #{id}")
    int deleteToApprove(Long id);

    @Select("SELECT id, companyName, workshop, submitTime FROM to_approve WHERE to_approve.companyName = #{companyName}")
    ToApprove getToApproveByName(String companyName);

    @Select("SELECT id, companyName, workshop, submitTime FROM to_approve WHERE to_approve.id = #{id}")
    ToApprove getToApproveById(Long id);

    @Select(("SELECT id, companyName, workshop, submitTime FROM to_approve ORDER BY submitTime LIMIT #{page}, #{size}"))
    List<ToApprove> getToApproveList(int page, int size);
}
