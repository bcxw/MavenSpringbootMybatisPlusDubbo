package com.zee.controller;

import com.zee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public String get() {
        userService.login("","");
        System.out.println("login cccccccc");
        return "to login";

    }

}
