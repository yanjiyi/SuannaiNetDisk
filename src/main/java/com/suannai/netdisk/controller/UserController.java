package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.LoginRequstData;
import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.SysConfig;
import com.suannai.netdisk.model.User;
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
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SysConfigService sysConfigService;

    @RequestMapping(value = "/login")
    public Message login(@RequestBody LoginRequstData requstData, HttpServletRequest request, HttpSession session)
    {
        Message message = new Message();
        message.setErrorMsg("登录成功！");
        message.setOperation("登录");
        message.setStatusCode(2000);

        String verifyCode = (String)session.getAttribute("verifyCode");
        if(verifyCode!=null)
        {
            if(verifyCode.equals(requstData.getVerifyCode()))
            {
                List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
                if(sysConfigs.isEmpty())
                {
                    message.setErrorMsg("已被管理员禁止登录！");
                    message.setStatusCode(5500);
                    return message;
                }else
                {
                    for(SysConfig sysConfig : sysConfigs)
                    {
                        if(sysConfig.getName().equals("AllowLogin") && sysConfig.getValue().equals("YES"))
                        {
                            User user = new User();
                            user.setUsername(requstData.getUsername());
                            user.setPassword(requstData.getPassword());

                            if(!userService.login(user, IPUtils.getIpAddr(request),session))
                            {
                                message.setStatusCode(4000);
                                message.setErrorMsg("登录失败！用户名或密码不正确！或用户处于封禁状态！");
                            }

                            return message;
                        }
                    }

                    message.setErrorMsg("已被管理员禁止登录！");
                    message.setStatusCode(5500);
                }
            }else
            {
                message.setErrorMsg("无法验证验证码！");
                message.setStatusCode(3300);
            }
        }

        return message;
    }

    @RequestMapping(value = "/register")
    public Message register(@RequestBody LoginRequstData requstData,HttpSession session)
    {
        Message message = new Message();
        message.setStatusCode(2000);
        message.setOperation("注册用户");
        message.setErrorMsg("操作成功！");

        String verifyCode = (String)session.getAttribute("verifyCode");
        if(verifyCode!=null)
        {
            List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
            if(sysConfigs.isEmpty())
            {
                message.setErrorMsg("已被管理员禁止注册！");
                message.setStatusCode(5500);
                return message;
            }else {
                for (SysConfig sysConfig : sysConfigs) {
                    if(sysConfig.getName().equals("AllowRegister") && sysConfig.getValue().equals("YES"))
                    {
                        User user = new User();
                        user.setUsername(requstData.getUsername());
                        user.setPassword(requstData.getPassword());

                        if(!userService.createUser(user))
                        {
                            message.setStatusCode(4000);
                            message.setErrorMsg("操作失败！或用户已存在！");
                        }

                        return message;
                    }
                }

                message.setErrorMsg("已被管理员禁止注册！");
                message.setStatusCode(5500);
            }
        }else
        {
            message.setStatusCode(3300);
            message.setErrorMsg("无法验证验证码！");
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

        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(sysConfigs.isEmpty())
        {
            message.setErrorMsg("已被管理员禁止注销！");
            message.setStatusCode(5500);
            return message;
        }else {
            for (SysConfig sysConfig : sysConfigs) {
                if(sysConfig.getName().equals("AllowLogout") && sysConfig.getValue().equals("YES"))
                {
                    session.setAttribute("user",null);

                    return message;
                }
            }

            message.setErrorMsg("已被管理员禁止注销！");
            message.setStatusCode(5500);
        }

        return message;
    }
}
