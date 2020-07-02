package com.mimu.springboot.mybatis.demo.service;

import com.mimu.springboot.mybatis.demo.mapper.term.TermDataMapper;
import com.mimu.springboot.mybatis.demo.mapper.user.UserDataMapper;
import com.mimu.springboot.mybatis.demo.model.TermInfo;
import com.mimu.springboot.mybatis.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/7/29
 */
@Service
public class CommonService {

    private TermDataMapper termDataMapper;
    private UserDataMapper userDataMapper;

    @Autowired
    public void setTermDataMapper(TermDataMapper termDataMapper) {
        this.termDataMapper = termDataMapper;
    }

    @Autowired
    private void setUserDataMapper(UserDataMapper userDataMapper) {
        this.userDataMapper = userDataMapper;
    }

    public TermInfo getTermData(long pid) {
        TermInfo data = termDataMapper.getTermInfoById(pid);
        return data != null ? data : new TermInfo();
    }

    public UserInfo getUserData(long pid) {
        UserInfo data = userDataMapper.getUserInfoById(pid);
        return data != null ? data : new UserInfo();
    }
}
