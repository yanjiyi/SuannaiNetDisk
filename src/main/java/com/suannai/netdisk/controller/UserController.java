package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.LoginRequstData;
import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysConfigService;
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

    @Autowired
    MainSvrService mainSvrService;

    @Autowired
    SysConfigService sysConfigService;

    @RequestMapping(value = "/api/login")
    public Message login(@RequestBody LoginRequstData requstData, HttpServletRequest request, HttpSession session) {
        Message message = new Message();
        message.setErrorMsg("登录成功！");
        message.setOperation("登录");
        message.setStatusCode(2000);

        String verifyCode = (String) session.getAttribute("verifyCode");
        if (verifyCode != null) {
            if (verifyCode.equals(requstData.getVerifyCode())) {

                if (sysConfigService.ConfigIsAllow("AllowLogin")) {
                    User user = new User();
                    user.setUsername(requstData.getUsername());
                    user.setPassword(requstData.getPassword());

                    if (!userService.login(user, IPUtils.getIpAddr(request), session)) {
                        message.setStatusCode(4000);
                        message.setErrorMsg("登录失败！用户名或密码不正确！或用户处于封禁状态！");
                    }

                    session.setAttribute("CurWorkDir", "/");

                    return message;
                } else {
                    message.setErrorMsg("已被管理员禁止登录！");
                    message.setStatusCode(5500);
                }
            }else{
                message.setErrorMsg("验证码不正确！");
                message.setStatusCode(6000);
            }
        } else {
            message.setErrorMsg("无法验证验证码！");
            message.setStatusCode(6000);
        }
        return message;
    }

    @RequestMapping(value = "/api/register")
    public Message register(@RequestBody LoginRequstData requstData, HttpSession session) {
        Message message = new Message();
        message.setStatusCode(2000);
        message.setOperation("注册用户");
        message.setErrorMsg("操作成功！");

        String verifyCode = (String) session.getAttribute("verifyCode");
        if (verifyCode != null) {
            if(verifyCode.equals(requstData.getVerifyCode())) {
                if (sysConfigService.ConfigIsAllow("AllowRegister")) {
                    User user = new User();
                    user.setUsername(requstData.getUsername());
                    user.setPassword(requstData.getPassword());

                    if (!userService.createUser(user)) {
                        message.setStatusCode(4000);
                        message.setErrorMsg("操作失败！或用户已存在！");
                    }

                    return message;
                } else {
                    message.setErrorMsg("已被管理员禁止注册！");
                    message.setStatusCode(5500);
                }
            }else {
                message.setStatusCode(6000);
                message.setErrorMsg("验证码不正确！");
            }
        } else {
            message.setStatusCode(3300);
            message.setErrorMsg("无法验证验证码！");
        }

        return message;
    }

    @RequestMapping(value = "/api/logout")
    public Message logout(HttpSession session, HttpServletResponse response) {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("注销用户！");
        message.setStatusCode(2000);


        if (sysConfigService.ConfigIsAllow("AllowLogout")) {
            session.setAttribute("user", null);

            return message;
        } else {
            message.setErrorMsg("已被管理员禁止注销！");
            message.setStatusCode(5500);
        }


        return message;
    }

    @RequestMapping(value = "/api/getUser")
    public User getUser(HttpSession session)
    {
        if(sysConfigService.ConfigIsAllow("AllowGetUser"))
        {
            return (User) session.getAttribute("user");
        }

        return null;
    }
}
