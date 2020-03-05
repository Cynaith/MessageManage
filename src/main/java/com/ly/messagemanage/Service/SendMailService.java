package com.ly.messagemanage.Service;

import com.ly.messagemanage.Entity.MailInfo;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

public interface SendMailService {

    boolean pushQueue(MailInfo mailInfo);
}
