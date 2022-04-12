package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.UserService;
import com.suannai.netdisk.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public Message login(@RequestBody User user, HttpServletRequest request, HttpSession session)
    {
        Message message = new Message();
        message.setErrorMsg("登录成功！");
        message.setOperation("登录");
        message.setStatusCode(2000);

        if(!userService.login(user, IPUtils.getIpAddr(request),session))
        {
            message.setStatusCode(4000);
            message.setErrorMsg("登录失败！用户名或密码不正确！或用户处于封禁状态！");
        }

        return message;
    }

    @RequestMapping(value = "/register")
    public Message register(@RequestBody User user)
    {
        Message message = new Message();
        message.setStatusCode(2000);
        message.setOperation("注册用户");
        message.setErrorMsg("操作成功！");

        if(!userService.createUser(user))
        {
            message.setStatusCode(4000);
            message.setErrorMsg("操作失败！或用户已存在！");
        }

        return message;
    }

    @RequestMapping(value = "/logout")
    public Message logout(HttpSession session, HttpServletResponse response)
    {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("注销用户！");
        message.setStatusCode(2000);

        session.setAttribute("user",null);

        return message;
    }
}
