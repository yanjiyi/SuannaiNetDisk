package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.SysConfigService;
import com.suannai.netdisk.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@RestController
public class TaskController {
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/deleteTask")
    public Message deleteTask(@RequestParam("taskid") int taskid, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();


        if (sysConfigService.ConfigIsAllow("AllowDeleteTask")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Task task = taskService.queryByID(taskid);
                if (task != null) {
                    if (!Objects.equals(task.getUserid(), user.getId())) {
                        message.setErrorMsg("不是当前用户事务！拒绝访问！");
                        message.setStatusCode(5300);
                    } else {
                        if (taskService.deleteTask(task)) {
                            message.setStatusCode(2000);
                            message.setErrorMsg("删除成功！");
                        } else {
                            message.setStatusCode(5600);
                            message.setErrorMsg("删除失败！");
                        }
                    }
                } else {
                    message.setStatusCode(5200);
                    message.setErrorMsg("无效事务ID");
                }
            } else response.sendRedirect("/index.html");
        } else {
            message.setErrorMsg("已被管理员禁止删除事务功能！");
            message.setStatusCode(5500);
        }

        return message;
    }
}
