package com.stu.worktracer.service;

import com.stu.worktracer.error.KnownException;

public interface CheckServiceInterface {
    int checkCheck(Long uid) throws KnownException;

    void checkIn(Long uid) throws KnownException;

    void checkOut(Long uid) throws KnownException;

    void survey(Long uid, Integer welfare) throws KnownException;

    Boolean checkSurvey(Long uid) throws KnownException;

    void processDailyRecord() throws KnownException;

    void clearSurveyed() throws KnownException;
}
