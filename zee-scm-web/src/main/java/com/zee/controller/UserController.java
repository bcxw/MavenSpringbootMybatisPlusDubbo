package com.zee.controller;

import com.zee.common.ResultUtil;
import com.zee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request,String username, String password) {
        Map<String, Object> result=userService.login(username,password);
        boolean success=Boolean.parseBoolean(result.get("success").toString());
        if(success){
            request.getSession().setAttribute("user",result.get("data"));
        }
        return result;

    }

    @RequestMapping("/getConfig")
    public Map<String, Object> getUser(HttpServletRequest request) {
        Map<String, Object> userMap= (Map<String, Object>) request.getSession().getAttribute("user");
        return ResultUtil.success(userMap);

    }

    @RequestMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResultUtil.success();

    }
    @RequestMapping("/getMenu")
    public Map<String, Object> getMenu(HttpServletRequest request) {
        Map<String, Object> userMap= (Map<String, Object>) request.getSession().getAttribute("user");
        return userService.getMenu(userMap.get("id").toString());
    }

}
