package com.stu.worktracer.redis;

import com.stu.worktracer.dto.DetailCompany;
import joptsimple.internal.Strings;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;


    //TODO
    public void setCompanyCache(DetailCompany company) {

    }

    //TODO
    public DetailCompany getCompanyCache(Long companyId) {
        return null;
    }

    public void setToken(Long uid, String token) {

        redisTemplate.opsForValue().set(token, uid.toString(), 15, TimeUnit.DAYS);


    }

    public Long getUid(String token) {

        String uidString = redisTemplate.opsForValue().get(token);
        if (uidString == null) {
            return null;
        }
        return Long.valueOf(uidString);
    }

    private static String KEY_SEP = ":";

    private static String companyId2Key(Long companyId) {
        return Strings.join(new String[]{"COM", companyId.toString()}, KEY_SEP);
    }


}
