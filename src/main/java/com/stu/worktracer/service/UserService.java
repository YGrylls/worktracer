package com.stu.worktracer.service;

import com.stu.worktracer.dao.*;
import com.stu.worktracer.dto.CheckCompanyRes;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.redis.RedisService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserService implements UserServiceInterface {

    private final UserMapper userMapper;

    private final RedisService redisService;

    private final HiredMapper hiredMapper;

    private final CompanyMapper companyMapper;

    public UserService(RedisService redisService, UserMapper userMapper, HiredMapper hiredMapper, CompanyMapper companyMapper) {
        this.redisService = redisService;
        this.userMapper = userMapper;
        this.hiredMapper = hiredMapper;
        this.companyMapper = companyMapper;
    }

    @Override
    public String login(String username, String password) throws KnownException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new KnownException(ErrCode.LOGIN_ERROR);
        }
        if (password == null || !password.equals(user.getPw())) {
            throw new KnownException(ErrCode.LOGIN_ERROR);
        }
        return createToken(user.getUid());
    }

    @Override
    public String register(String username, String password) throws KnownException {
        User exist = userMapper.getUserByUsername(username);
        if (exist != null) {
            throw new KnownException(ErrCode.USERNAME_EXIST_ERROR);
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPw(password);
        userMapper.addUser(newUser);
        return login(username, password);
    }

    @Override
    public void modifyCom(Long uid, Long companyId) throws KnownException {
        Company com = companyMapper.getCompanyById(companyId);
        if (com == null) {
            throw new KnownException(ErrCode.COMPANY_NOT_EXIST_ERROR);
        }
        Hired hired = hiredMapper.getHiredByUid(uid);
        if (hired == null) {
            Hired newH = new Hired();
            newH.setCompanyId(companyId);
            newH.setUid(uid);
            newH.setModTime(new Date());
            hiredMapper.addHired(newH);
        } else {
            Date modTime = hired.getModTime();
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MONTH, -1);
            Date validTime = now.getTime();
            if (!modTime.before(validTime)) {
                throw new KnownException(ErrCode.MODIFY_COLD_DOWN_ERROR);
            }
            Hired newH = new Hired();
            newH.setCompanyId(companyId);
            newH.setUid(uid);
            newH.setModTime(new Date());
            hiredMapper.updateHired(newH);
        }

    }

    @Override
    public CheckCompanyRes checkCom(Long uid) throws KnownException {
        Hired hired = hiredMapper.getHiredByUid(uid);
        CheckCompanyRes res = new CheckCompanyRes();
        if (hired == null || hired.getCompanyId() == null) {
            res.setValid(false);
            return res;
        }
        Company com = companyMapper.getCompanyById(hired.getCompanyId());
        if (com == null) {
            res.setValid(false);
            return res;
        }
        res.setValid(true);
        res.setCompanyId(com.getCompanyId());
        res.setCompanyName(com.getName());
        return res;
    }

    private String createToken(Long uid) {
        Long now = new Date().getTime();
        String str = uid.toString() + ":" + now.toString();
        String token = DigestUtils.sha256Hex(str);
        redisService.setToken(uid, token);
        return token;
    }
}
