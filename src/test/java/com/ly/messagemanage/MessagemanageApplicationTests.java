package com.ly.messagemanage;

import com.ly.messagemanage.Entity.MailInfo;
import com.ly.messagemanage.Service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessagemanageApplicationTests {

    @Autowired
    SendMailService sendMailService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            MailInfo mailInfo = new MailInfo("mailId:" + i, "mailId:" + i, "mailId:" + i, "mailId:" + i, "mailId:" + i);
            sendMailService.pushQueue(mailInfo);
        }

    }

}
