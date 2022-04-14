package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.SysConfig;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class FriendController {
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    TaskTypeService taskTypeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addFriend")
    public Message addFriend(@RequestParam("username") String username, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();

        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(sysConfigs.isEmpty())
        {
            message.setErrorMsg("已被管理员拒绝添加好友！");
            message.setStatusCode(5500);
        }else {
            for(SysConfig sysConfig : sysConfigs)
            {
                if(sysConfig.getName().equals("AllowAddFriend")&&sysConfig.getValue().equals("YES"))
                {
                    User user = (User) session.getAttribute("user");
                    if(user!=null)
                    {
                        User friendUser = userService.QueryByName(username);
                        if(friendUser!=null)
                        {
                            int friendType = taskTypeService.GetTaskID("Friend");
                            if(friendType>0)
                            {
                                Task task = new Task();
                                task.setIdle(true);
                                task.setTaskstatus(false);
                                task.setDate(new Date());
                                task.setTargetid(friendUser.getId());
                                task.setAdditional(-1);
                                task.setTasktype(friendType);
                                task.setUserid(user.getId());

                                if(taskService.createTask(task))
                                {
                                    message.setErrorMsg("添加好友申请成功！");
                                    message.setStatusCode(2000);
                                }else {
                                    message.setErrorMsg("添加事务失败！");
                                    message.setStatusCode(5600);
                                }
                            }else {
                                message.setStatusCode(5400);
                                message.setErrorMsg("无此事务类型！");
                            }

                        }else {
                            message.setStatusCode(5200);
                            message.setErrorMsg("无此用户！");
                        }
                    }else{
                        response.sendRedirect("/index.html");
                    }
                }
            }

            message.setErrorMsg("已被管理员拒绝添加好友！");
            message.setStatusCode(5500);
        }

        return message;
    }

    @RequestMapping(value = "/accecptFriend")
    public Message acceptFriend(@RequestParam("taskid") int taskid,HttpServletResponse response,HttpSession session) throws IOException {
        Message message = new Message();

        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(sysConfigs.isEmpty())
        {
            message.setStatusCode(5500);
            message.setErrorMsg("已被管理员禁止接受好友功能！");
        }else{
            for(SysConfig sysConfig : sysConfigs)
            {
                if(sysConfig.getName().equals("AllowAcceptFriend")&&sysConfig.getValue().equals("YES"))
                {
                    User user = (User) session.getAttribute("user");
                    if(user!=null)
                    {
                        Task task = taskService.queryByID(taskid);
                        if(task!=null)
                        {
                            if(task.getTasktype() != taskTypeService.GetTaskID("Friend"))
                            {
                                message.setStatusCode(5200);
                                message.setErrorMsg("不匹配的事务类型！");
                            }else{
                                if(!Objects.equals(task.getTargetid(), user.getId()))
                                {
                                    message.setErrorMsg("不是该用户事务！拒绝访问！");
                                    message.setStatusCode(5300);
                                }else {

                                }
                            }
                        }else {
                            message.setErrorMsg("无效事务ID");
                            message.setStatusCode(5100);
                        }
                    }else {
                        response.sendRedirect("/index.html");
                    }
                }
            }

            message.setStatusCode(5500);
            message.setErrorMsg("已被管理员禁止接受好友功能！");
        }

        return message;
    }
}
