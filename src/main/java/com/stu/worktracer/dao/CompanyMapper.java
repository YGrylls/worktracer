package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyMapper {

    @Insert("INSERT INTO company (name, workshop, rate, welfare) VALUES (#{name}, #{workshop}, #{rate}, #{welfare})")
    int addCompany(Company company);

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
