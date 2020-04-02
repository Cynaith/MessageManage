package com.ly.messagemanage.Controller;

import com.ly.messagemanage.Domain.Mail;
import com.ly.messagemanage.Domain.User;
import com.ly.messagemanage.Service.Impl.LoginServiceImpl;
import com.ly.messagemanage.Service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/

@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String Login(User user) {
        return loginService.login(user);
    }

    @RequestMapping("/register")
    public void register(@RequestParam("name") String name, @RequestParam("password") String password) {
        loginService.register(name, password);
    }

    @RequestMapping("/checkmail")
    public String CheckMail(@Param("mail") String mail, @Param("password") String password, @Param("name") String name) {
        Mail checkmail = new Mail();
        checkmail.setMail(mail);
        checkmail.setPassword(password);
        loginService.checkmail(checkmail, name);
        return "true";
    }

    @RequestMapping("/checkclient")
    public String CheckClient(@Param("clientkey") String clientkey, @Param("name") String name,
                              @Param("mail") String mail, @Param("password") String password) {
        User user = new User();
        user.setClientkey(clientkey);
        user.setName(name);
        Mail mail1 = new Mail();
        mail1.setMail(mail);
        mail1.setPassword(password);
        return loginService.checkClient(user, mail1) == "true" ? "Verify success" : "Verify false";
    }

    @RequestMapping("getUserMail")
    public String getUserMail(@Param("name") String name) {
        return loginService.getUsermail(name);
    }
}
