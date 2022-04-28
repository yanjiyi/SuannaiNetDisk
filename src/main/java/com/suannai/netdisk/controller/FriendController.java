package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.Friend;
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

    @RequestMapping(value = "/api/addFriend")
    public Message addFriend(@RequestParam("username") String username, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();


        if (sysConfigService.ConfigIsAllow("AllowAddFriend")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                User friendUser = userService.QueryByName(username);
                if (friendUser != null) {
                    int friendType = taskTypeService.GetTaskID("Friend");
                    if (friendType > 0) {
                        Task task = new Task();
                        task.setIdle(true);
                        task.setTaskstatus(false);
                        task.setDate(new Date());
                        task.setTargetid(friendUser.getId());
                        task.setAdditional(-1);
                        task.setTasktype(friendType);
                        task.setUserid(user.getId());

                        if (taskService.createTask(task)) {
                            message.setErrorMsg("添加好友申请成功！");
                            message.setStatusCode(2000);
                        } else {
                            message.setErrorMsg("添加事务失败！");
                            message.setStatusCode(5600);
                        }
                    } else {
                        message.setStatusCode(5400);
                        message.setErrorMsg("无此事务类型！");
                    }

                } else {
                    message.setStatusCode(5200);
                    message.setErrorMsg("无此用户！");
                }
            } else {
                response.sendRedirect("/index.html");
            }
        } else {


            message.setErrorMsg("已被管理员拒绝添加好友！");
            message.setStatusCode(5500);
        }

        return message;
    }

    @RequestMapping(value = "/api/accecptFriend")
    public Message acceptFriend(@RequestParam("taskid") int taskid, HttpServletResponse response, HttpSession session) throws IOException {
        Message message = new Message();

        if (sysConfigService.ConfigIsAllow("AllowAcceptFriend")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Task task = taskService.queryByID(taskid);
                if (task != null) {
                    if (task.getTasktype() != taskTypeService.GetTaskID("Friend")) {
                        message.setStatusCode(5200);
                        message.setErrorMsg("不匹配的事务类型！");
                    } else {
                        if (!Objects.equals(task.getTargetid(), user.getId())) {
                            message.setErrorMsg("不是该用户事务！拒绝访问！");
                            message.setStatusCode(5300);
                        } else {
                            User friUser = userService.QueryByID(task.getTargetid());
                            if (friUser == null) {
                                message.setErrorMsg("无此用户！");
                                message.setStatusCode(5900);
                                return message;
                            }
                            Friend friend = new Friend();
                            friend.setDate(new Date());
                            friend.setFrdid(task.getTargetid());
                            friend.setOwnner(user.getId());
                            friend.setWho(friUser.getUsername());

                            boolean status = friendService.addFriend(friend);

                            friend.setOwnner(task.getTargetid());
                            friend.setFrdid(user.getId());
                            friend.setWho(user.getUsername());

                            if (status && friendService.addFriend(friend)) {
                                task.setTaskstatus(true);
                                taskService.updateTask(task);

                                message.setStatusCode(2000);
                                message.setErrorMsg("操作成功！");
                            } else {
                                message.setStatusCode(5700);
                                message.setErrorMsg("操作失败！");
                            }
                        }
                    }
                } else {
                    message.setErrorMsg("无效事务ID");
                    message.setStatusCode(5100);
                }
            } else {
                response.sendRedirect("/index.html");
            }
        } else {

            message.setStatusCode(5500);
            message.setErrorMsg("已被管理员禁止接受好友功能！");
        }

        return message;
    }

    @RequestMapping(value = "/api/refuseFriend")
    public Message refuseFriend(@RequestParam("taskid") int taskid, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();


        if (sysConfigService.ConfigIsAllow("AllowRefuseFriend")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Task task = taskService.queryByID(taskid);
                if (task != null) {
                    int friType = taskTypeService.GetTaskID("Friend");
                    if (friType > 0) {
                        if (friType == task.getTasktype()) {
                            if (taskService.lockTask(task)) {
                                message.setErrorMsg("拒绝成功！");
                                message.setStatusCode(2000);
                            } else {
                                message.setStatusCode(5400);
                                message.setErrorMsg("拒绝失败！");
                            }
                        } else {
                            message.setStatusCode(5200);
                            message.setErrorMsg("事务类型不匹配！");
                        }
                    } else {
                        message.setErrorMsg("无效事务类型！");
                        message.setStatusCode(5200);
                    }
                } else {
                    message.setStatusCode(5100);
                    message.setErrorMsg("无效事务ID");
                }
            } else response.sendRedirect("/index.html");
        } else {

            message.setErrorMsg("已被管理员禁止拒绝好友！");
            message.setStatusCode(5500);
        }

        return message;
    }

    @RequestMapping(value = "/api/listMyFriends")
    public List<Friend> listMyFriends(HttpServletResponse response, HttpSession session)
    {
        if(sysConfigService.ConfigIsAllow("ListMyFriends"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                return friendService.listMyFriends(user.getId());
            }
        }

        return null;
    }
}
