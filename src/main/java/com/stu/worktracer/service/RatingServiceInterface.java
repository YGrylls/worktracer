package com.stu.worktracer.service;

import com.stu.worktracer.dto.DetailCompany;

public interface RatingServiceInterface {
    void refreshAllRatings();

    DetailCompany getRating(Long companyId);
}
