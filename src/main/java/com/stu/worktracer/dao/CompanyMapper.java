package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyMapper {

    @Insert("INSERT INTO company (name, workshop, rate, welfare) VALUES (#{name}, #{workshop}, #{rate}, #{welfare})")
    @SelectKey(statement = "SELECT last_insert_id()", keyProperty = "companyId", resultType = Long.class, before = false)
    Long addCompany(Company company);

    @Select("SELECT companyId, name, workshop, rate, welfare FROM company WHERE company.companyId = #{companyId}")
    Company getCompanyById(Long companyId);

    @Select("SELECT companyId, name, workshop, rate, welfare FROM company WHERE company.name = #{name}")
    Company getCompanyByName(String name);

    @Select("<script>" +
            "SELECT companyId, name, workshop, rate, welfare FROM company WHERE company.companyId IN " +
            "<foreach item = 'item' index = 'index' collection = 'ids' open='(' close=')' separator=','> #{item} </foreach>" +
            "</script>")
    List<Company> getCompanyByList(@Param("ids") List<Long> ids);

}
