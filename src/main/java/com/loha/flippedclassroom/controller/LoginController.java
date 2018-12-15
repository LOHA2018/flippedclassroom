package com.loha.flippedclassroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制登录界面
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Controller
public class LoginController {

    @GetMapping(value = "/pc/login")
    public String loginOnPc(){
        return "pc/PcLoginPage";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
