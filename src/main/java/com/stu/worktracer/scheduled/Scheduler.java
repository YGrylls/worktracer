package com.stu.worktracer.scheduled;

import com.stu.worktracer.service.CheckServiceInterface;
import com.stu.worktracer.service.RatingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private RatingServiceInterface ratingService;

    @Autowired
    private CheckServiceInterface checkService;

    @Scheduled(cron = "0 0 4 1 * ? *")
    public void monthlyRating() {
        try {
            ratingService.refreshAllRatings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0 4 * * ? ")
    public void dailyCollect() {
        try {
            checkService.processDailyRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
