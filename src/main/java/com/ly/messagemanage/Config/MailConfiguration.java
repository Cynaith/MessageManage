package com.ly.messagemanage.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @USER: lynn
 * @DATE: 2020/3/6
 **/
@Configuration
public class MailConfiguration {


    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        return mailSender;

    }
}
