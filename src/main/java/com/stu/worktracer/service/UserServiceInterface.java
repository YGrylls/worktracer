package com.stu.worktracer.service;

import com.stu.worktracer.dto.CheckCompanyRes;
import com.stu.worktracer.error.KnownException;

public interface UserServiceInterface {

    String login(String username, String password) throws KnownException;

    String register(String username, String password) throws KnownException;

    void modifyCom(Long uid, Long companyId) throws KnownException;

    CheckCompanyRes checkCom(Long uid) throws KnownException;

}
