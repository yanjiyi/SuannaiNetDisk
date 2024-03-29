package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.model.TaskType;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.SysConfigService;
import com.suannai.netdisk.service.TaskService;
import com.suannai.netdisk.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskTypeService taskTypeService;

    @RequestMapping(value = "/api/deleteTask")
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

    @RequestMapping(value = "/api/listMyTask")
    public List<Task> listMyTask(HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowListMyTask"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                return taskService.queryUserTask(user.getId());
            }else response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/listToMyTask")
    public List<Task> listToMyTask(HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowListToMyTask"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                return taskService.queryToUserTask(user.getId());
            }else response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/getTaskType")
    public String getTaskType(@RequestParam("taskID") int ID)
    {
        if(sysConfigService.ConfigIsAllow("AllowGetTaskType"))
        {
            return taskTypeService.GetTaskTypeName(ID);
        }

        return "None";
    }

    @RequestMapping(value = "/api/listAllTaskType")
    public List<TaskType> listAllTaskType(HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AlloListAllTaskType"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                return taskTypeService.queryAll();
            }else response.sendRedirect("/index.html");
        }
        return null;
    }

    @RequestMapping(value = "/api/getTaskDeleteUrl")
    public String getTaskDeleteUrl(@RequestParam("id") int id,HttpSession session,HttpServletResponse response)
    {
        if(sysConfigService.ConfigIsAllow("AllowGetTaskDeleteUrl"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Task task = taskService.queryByID(id);
                if(task!=null)
                {
                    String name = taskTypeService.GetTaskTypeName(task.getTasktype());
                    if(name=="Download")
                    {
                        return "/api/deleteDownloadTask";
                    }

                    return "/api/deleteTask";
                }
            }
        }

        return "#";
    }

    @RequestMapping(value = "/api/queryTask")
    public Task queryTask(@RequestParam("id") int id,HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowQueryTask"))
        {
            User user = (User) session.getAttribute("user");

            if(user!=null)
            {
                Task task = taskService.queryByID(id);
                if(task!=null)
                    return task;

            }else response.sendRedirect("/index.html");
        }

        return null;
    }
}
