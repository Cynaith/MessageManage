package com.ly.messagemanage.Service.Impl;

import com.ly.messagemanage.Entity.MailInfo;
import com.ly.messagemanage.Handler.Mail.MailProducer;
import com.ly.messagemanage.Service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    MailProducer mailProducer;

    @Override
    public boolean pushQueue(MailInfo mailInfo) {

        return mailProducer.sendMessage(mailInfo);
    }
}
