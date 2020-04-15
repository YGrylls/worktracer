package com.stu.worktracer;

import com.stu.worktracer.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataTests {

    @Autowired
    RatingService ratingService;

    @Test
    public void testRating() {
        int res = ratingService.calRate(40, 80);
        System.out.println(res);
        int res1 = ratingService.calRate(90, 100);
        System.out.println(res1);
    }
}
