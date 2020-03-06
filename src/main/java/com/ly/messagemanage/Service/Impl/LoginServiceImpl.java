package com.ly.messagemanage.Service.Impl;

import com.ly.messagemanage.Domain.User;
import com.ly.messagemanage.Mapper.UserMapping;
import com.ly.messagemanage.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapping userMapping;
    @Override
    public String login(User user) {
        return userMapping.login(user)>0?"true":"false";
    }
}
