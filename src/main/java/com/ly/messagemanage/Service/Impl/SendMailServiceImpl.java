package com.ly.messagemanage.Service.Impl;

import com.ly.messagemanage.Entity.MailInfo;
import com.ly.messagemanage.Handler.Mail.MailProducer;
import com.ly.messagemanage.Mapper.MailMapping;
import com.ly.messagemanage.Mapper.UserMapping;
import com.ly.messagemanage.Service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    MailProducer mailProducer;

    @Autowired
    MailMapping mailMapping;

    @Autowired
    UserMapping userMapping;

    @Override
    @Async
    public void pushQueue(MailInfo mailInfo) {
        mailInfo.setMailSmtp(mailMapping.getPasswordByMail(mailInfo.getProduceMail()));
        mailProducer.sendMessage(mailInfo);
    }

    @Override
    @Async
    public void addMailRecord(MailInfo mailInfo) {
        int id  = userMapping.getIdByMail(mailInfo.getProduceMail());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        mailMapping.addMailLog(id,mailInfo.getProduceMail(),mailInfo.getConsumeMail(),mailInfo.getMailSubject(),mailInfo.getMailText(),time);
    }


}
