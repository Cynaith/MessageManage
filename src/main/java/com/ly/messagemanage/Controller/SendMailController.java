package com.ly.messagemanage.Controller;

import com.ly.messagemanage.Entity.MailInfo;
import com.ly.messagemanage.Service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

@Controller
@RequestMapping("/mail")
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;

    @ResponseBody
    @RequestMapping("/send")
    public boolean sendMail(MailInfo mailInfo){
        sendMailService.pushQueue(mailInfo);
        return true;
    }

    public boolean setMail(){
        return true;
    }
}
