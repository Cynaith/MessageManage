package com.ly.messagemanage.Entity;

import lombok.*;

import java.io.Serializable;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo implements Serializable {

    //生产者邮箱
    String produceMail;

    //消费者邮箱
    String consumeMail;

    //邮件主题
    String mailSubject;

    //纯文本内容
    String mailText;

    //邮箱SMTP码
    String mailSmtp;
}
