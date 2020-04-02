package com.ly.messagemanage.Service;

import com.ly.messagemanage.Domain.Mail;
import com.ly.messagemanage.Domain.User;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/
public interface LoginService {
    String login(User user);
    String checkmail(Mail mail,String name);
    String checkClient(User user,Mail mail);
    String initMail(Mail mail,String name);
    String getUsermail(String name);
}
