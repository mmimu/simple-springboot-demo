package com.mimu.springboot.mybatis.demo.controller;

import com.mimu.springboot.mybatis.demo.model.TermInfo;
import com.mimu.springboot.mybatis.demo.model.UserInfo;
import com.mimu.springboot.mybatis.demo.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/6/29
 */
@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/sbm/user/info.go", method = RequestMethod.GET)
    public TermInfo getTermInfo(long pid) {
        TermInfo termData = commonService.getTermData(pid);
        logger.info("userInfo={}", termData);
        return termData;
    }

    @RequestMapping(value = "/sbm/term/info.go", method = RequestMethod.GET)
    public UserInfo getUserInfo(long pid) {
        UserInfo userData = commonService.getUserData(pid);
        logger.info("userInfo={}", userData);
        return userData;
    }

}
