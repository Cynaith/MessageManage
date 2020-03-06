package com.ly.messagemanage.Controller;

import com.ly.messagemanage.Domain.User;
import com.ly.messagemanage.Service.Impl.LoginServiceImpl;
import com.ly.messagemanage.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ResponseBody
    @RequestMapping("/login")
    public String Login(User user){
        return loginService.login(user);
    }
}
