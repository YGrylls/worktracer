package com.stu.worktracer.service;

import com.stu.worktracer.dao.ToApprove;
import com.stu.worktracer.error.KnownException;

import java.util.List;

public interface AdminServiceInterface {
    List<ToApprove> getList(int page, int size) throws KnownException;

    void approve(Long id) throws KnownException;

    void decline(Long id) throws KnownException;
}
