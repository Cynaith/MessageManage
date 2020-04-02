package com.ly.messagemanage.Service.Impl;

import com.ly.messagemanage.Domain.Mail;
import com.ly.messagemanage.Domain.User;
import com.ly.messagemanage.Entity.MailInfo;
import com.ly.messagemanage.Handler.Mail.MailProducer;
import com.ly.messagemanage.Mapper.UserMapping;
import com.ly.messagemanage.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapping userMapping;
    @Autowired
    MailProducer mailProducer;

    @Override
    public String login(User user) {
        return userMapping.login(user) > 0 ? "true" : "false";
    }

    /**
     * @param mail 前端数据转发至mailProducer 设置key保存至user表用于验证邮箱
     * @param name
     * @return
     */
    @Override
    @Async
    public String checkmail(Mail mail, String name) {
        String uuid = UUID.randomUUID().toString();
        User user = new User();
        user.setName(name);
        user.setClientkey(uuid);
//        数据库写入key
        System.out.println(user.toString());
        userMapping.setClientkey(user);

        MailInfo mailInfo = new MailInfo();
        mailInfo.setProduceMail(mail.getMail());
        mailInfo.setConsumeMail(mail.getMail());
        mailInfo.setMailSmtp(mail.getPassword());
        mailInfo.setMailSubject("邮箱验证");
        mailInfo.setMailText("点此验证邮箱--->http://47.101.171.252:8081/user/checkclient?name=" + name + "&clientkey=" +
                uuid+"&mail="+mail.getMail()+"&password="+mail.getPassword());
        //推送至生产者中
        boolean result = mailProducer.sendMessage(mailInfo);

        return "" + result;
    }

    //邮箱客户端验证
    @Override
    public String checkClient(User user,Mail mail) {
        return userMapping.checkClient(user) > 0 ? this.initMail(mail,user.getName()) : "false";
    }

    //注册邮箱
    @Override
    public String initMail(Mail mail,String name) {
        userMapping.initMail(mail.getMail(),mail.getPassword(),name);
        return "true";
    }


    @Override
    public String getUsermail(String name) {
        String mail;
        try{
             mail = userMapping.getUsermail(name);
        }catch (Exception e){
            return "请先验证邮箱";
        }
        return mail;
    }
}
