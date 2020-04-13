package com.stu.worktracer.redis;

import com.stu.worktracer.dto.DetailCompany;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
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
        String key = uid2Key(uid);
        try {
            redisTemplate.opsForValue().set(key, token, 15, TimeUnit.DAYS);
        } catch (Exception e) {
            throw new KnownException(ErrCode.INTERNAL_SERVER_ERROR, e);
        }

    }

    public String getToken(Long uid) {
        String key = uid2Key(uid);
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            throw new KnownException(ErrCode.INTERNAL_SERVER_ERROR, e);
        }
    }

    private static String KEY_SEP = ":";

    private static String uid2Key(Long uid) {
        return Strings.join(new String[]{"USER", uid.toString()}, KEY_SEP);
    }

    private static String companyId2Key(Long companyId) {
        return Strings.join(new String[]{"COM", companyId.toString()}, KEY_SEP);
    }


}
