package com.stu.worktracer.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT uid, username, pw FROM user WHERE user.username = #{username}")
    User getUserByUsername(String username);


    @Insert("INSERT INTO user (username, pw) VALUES (#{username}, #{pw})")
    int addUser(User user);


    @Select("SELECT uid, username, surveyed FROM user WHERE user.uid = #{uid}")
    User getUserSurveyByUid(Long uid);

    @Update("UPDATE user SET user.surveyed = 1 WHERE user.uid = #{uid}")
    int updateSurveyedByUid(Long uid);

    @Update("UPDATE user SET user.surveyed = 0")
    int clearAllSurveyed();
}
