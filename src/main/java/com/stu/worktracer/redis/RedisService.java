package com.stu.worktracer.redis;

import com.alibaba.fastjson.JSON;
import com.stu.worktracer.dto.DetailCompany;
import joptsimple.internal.Strings;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;


    public void setCompanyCache(DetailCompany company) {
        String cache = Company2JSON(company);
        redisTemplate.opsForValue().set(companyId2Key(company.getCompanyId()), cache, 1, TimeUnit.DAYS);
    }

    public DetailCompany getCompanyCache(Long companyId) {
        String cache = redisTemplate.opsForValue().get(companyId2Key(companyId));
        if (StringUtils.isEmpty(cache)) {
            return null;
        }
        return JSON2Company(cache);
    }

    public void setToken(Long uid, String token) {
        redisTemplate.opsForValue().set(token, uid.toString(), 15, TimeUnit.DAYS);
    }

    /**
     * @param host client hostname
     * @return if cd has passed
     */
    public boolean getSetOpenCD(String host) {
        Boolean res = redisTemplate.opsForValue().setIfPresent(host, "", 15, TimeUnit.SECONDS);
        return res;
    }


    private DetailCompany JSON2Company(String json) {
        return JSON.parseObject(json, DetailCompany.class);
    }

    private String Company2JSON(DetailCompany dc) {
        return JSON.toJSONString(dc);
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
