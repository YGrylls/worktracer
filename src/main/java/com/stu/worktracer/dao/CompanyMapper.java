package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CompanyMapper {

    @Insert("INSERT INTO company (name, workshop) VALUES (#{name}, #{workshop})")
    int addCompany(Company company);

    @Select("SELECT companyId, name, workshop FROM company WHERE company.companyId = #{companyId}")
    Company getCompanyById(Long companyId);

    @Select("SELECT companyId, name, workshop FROM company WHERE company.name = #{name}")
    Company getCompanyByName(String name);
}
