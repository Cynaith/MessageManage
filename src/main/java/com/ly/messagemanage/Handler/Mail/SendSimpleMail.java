package com.ly.messagemanage.Handler.Mail;

import com.ly.messagemanage.Entity.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @USER: lynn
 * @DATE: 2020/3/6
 **/
@Component
public class SendSimpleMail {

    /**
     * @Autowired注入Spring Bean，则当前类必须也是Spring Bean才能调用它，
     * 不能用new xxx()来获得对象，这种方式获得的对象无法调用@Autowired注入的Bean
     */
    @Autowired
    JavaMailSenderImpl mailSender;


    public void sendMail(MailInfo mailInfo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailInfo.getProduceMail());
        simpleMailMessage.setTo(mailInfo.getConsumeMail());
        simpleMailMessage.setText(mailInfo.getMailText());
        simpleMailMessage.setSubject(mailInfo.getMailSubject());

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.starttls.required","true");
        properties.setProperty("mail.smtp.port", "587");

        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername(mailInfo.getProduceMail());
//        "bzbplhwrcippbbje"
        mailSender.setPassword(mailInfo.getMailSmtp());
        mailSender.setJavaMailProperties(properties);
        mailSender.send(simpleMailMessage);

    }
}
