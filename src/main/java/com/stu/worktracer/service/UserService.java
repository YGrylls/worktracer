package com.stu.worktracer.service;

import com.stu.worktracer.error.KnownException;

public class UserService implements UserServiceInterface {
    @Override
    public String login(String username, String password) throws KnownException {
        return null;
    }

    @Override
    public void register(String username, String password) throws KnownException {

    }

    @Override
    public void modifyCom(Long uid, Long companyId) throws KnownException {

    }

    @Override
    public Boolean checkCom(Long uid) throws KnownException {
        return null;
    }
}
