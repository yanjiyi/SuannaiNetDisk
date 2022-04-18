package com.suannai.netdisk.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suannai.netdisk.dao.AddUriOption;
import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.dao.OfficeTaskRespData;
import com.suannai.netdisk.dao.tellStatus;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.*;
import com.suannai.netdisk.utils.Aria2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class OfflineDownloadTaskController {
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    UserService userService;

    @Autowired
    TaskTypeService taskTypeService;

    @Autowired
    TaskService taskService;

    @Autowired
    SysFileTabService sysFileTabService;

    @Autowired
    MainSvrService mainSvrService;

    @RequestMapping(value = "/api/addDownloadTask")
    public OfficeTaskRespData addDownloadTask(@RequestParam("url") String url, HttpServletResponse response, HttpSession session) throws IOException {
        OfficeTaskRespData officeTaskRespData = new OfficeTaskRespData();

        if(sysConfigService.ConfigIsAllow("AllowAddDownloadTask"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                String dir = sysConfigService.GetConfig("downloadDir").getValue();
                if(dir==null||dir.equals(""))
                {
                    dir = "/data/netdisk/download";
                }

                dir+= File.separator + user.getUsername() + File.separator + UUID.randomUUID();

                File testDir = new File(dir);
                if(!testDir.exists())
                    testDir.mkdirs();

                AddUriOption addUriOption = new AddUriOption();
                addUriOption.setDir(dir);
                JSONObject jsonObject = JSON.parseObject(Aria2Utils.addUri("http://localhost:6800/jsonrpc",new String[]{url},addUriOption));

                JSONObject statusObject = JSON.parseObject(Aria2Utils.tellStatus("http://localhost:6800/jsonrpc",jsonObject.getString("result")));

                String path = jsonObject.getJSONArray("files").getJSONObject(0).getString("path");
                long length = Long.parseLong(jsonObject.getJSONArray("files").getJSONObject(0).getString("length"));
                String status = jsonObject.getString("status");

                SysFileTab sysFileTab = new SysFileTab();
                sysFileTab.setLocation(path);
                sysFileTab.setFilesize(length);
                sysFileTab.setFilehash("-1");
                sysFileTab.setRootmask(false);
                sysFileTab.setInuse(false);
                sysFileTab.setFilename(Paths.get(path).getFileName().toString());

                sysFileTabService.addRecord(sysFileTab);

                SysFileTab curSysFileTab = sysFileTabService.findByLocation(sysFileTab.getLocation());
                if(curSysFileTab!=null&&!StringUtils.isEmpty(curSysFileTab.getLocation()))
                {
                    Service workDir = (Service) session.getAttribute("curService");
                    if(workDir==null)
                    {
                        workDir = mainSvrService.getUserDirRecord(user,(String) session.getAttribute("CurWorkDir"));
                        if(workDir==null)
                        {
                            officeTaskRespData.setMessage("无法找到当前工作目录Service");
                            officeTaskRespData.setStatusCode(6000);

                            Aria2Utils.remove("http://localhost:6800/jsonrpc",jsonObject.getString("result"));
                            File file = new File(curSysFileTab.getLocation());
                            if(file.exists())
                            {
                                file.delete();
                            }

                            File fdir = new File(dir);
                            if(fdir.exists()&&fdir.isDirectory()&&fdir.list()==null)
                            {
                                fdir.delete();
                            }

                            sysFileTabService.deleteIfUnUse(curSysFileTab.getId()); //Delete Record

                            return officeTaskRespData;
                        }
                    }

                    Service service = new Service();
                    service.setUploaddate(new Date());
                    service.setDirmask(false);
                    service.setUserfilename(sysFileTab.getFilename());
                    service.setUserid(user.getId());
                    service.setParentid(workDir.getId());
                    service.setStatus(false);
                    service.setSysfilerecordid(curSysFileTab.getId());
                    mainSvrService.addFile(service);

                    Service targetService = mainSvrService.getWithSysFileTabID(curSysFileTab.getId()).get(0);
                    if(taskService!=null)
                    {
                        Task task = new Task();
                        task.setUserid(user.getId());
                        task.setTasktype(taskTypeService.GetTaskID("Download"));
                        task.setTargetid(0);
                        task.setAdditional(targetService.getId());
                        task.setDate(new Date());
                        task.setTaskstatus(false);
                        task.setAria2id(jsonObject.getString("id"));
                        task.setIdle(false);
                        task.setGid(jsonObject.getString("result"));

                        taskService.createTask(task);
                    }else {
                        Aria2Utils.remove("http://localhost:6800/jsonrpc",jsonObject.getString("result"));

                        File operFile = new File(curSysFileTab.getLocation());
                        File operParentFile = operFile.getParentFile();
                        if(operFile.exists())
                        {
                            operFile.delete();
                        }

                        if(operParentFile.exists()&&operParentFile.list()==null)
                        {
                            operParentFile.delete();
                        }

                        sysFileTabService.deleteIfUnUse(curSysFileTab.getId());

                        officeTaskRespData.setStatusCode(6100);
                        officeTaskRespData.setMessage("创建Task记录失败！");

                        return officeTaskRespData;

                    }

                }


                officeTaskRespData.setGid(jsonObject.getString("result"));
                officeTaskRespData.setAria2id(jsonObject.getString("id"));
                officeTaskRespData.setMessage("下载请求已发送！");
                officeTaskRespData.setStatusCode(2000);
                officeTaskRespData.setAria2status(status);
                officeTaskRespData.setLength(length);

            }else response.sendRedirect("/index.html");
        }else {
            officeTaskRespData.setMessage("已被管理员禁止建立离线下载任务！");
            officeTaskRespData.setStatusCode(5500);
        }

        return officeTaskRespData;
    }

    @RequestMapping(value = "/api/deleteDownloadTask")
    public Message deleteDownloadTask(@RequestParam("taskid") int taskid,HttpSession session,HttpServletResponse response) throws IOException {
        Message message = new Message();

        if(sysConfigService.ConfigIsAllow("AllowDeleteDownloadTask"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Task task = taskService.queryByID(taskid);
                if(task!=null)
                {
                    if(task.getUserid().equals(user.getId()))
                    {
                        if(task.getTasktype() == taskTypeService.GetTaskID("Download"))
                        {
                            if(!StringUtils.isEmpty(task.getGid()))
                            {
                                JSONObject object = JSON.parseObject(Aria2Utils.remove("http://localhost:6800/jsonrpc",task.getGid()));
                                if(object.getString("result").equals(task.getGid()))
                                {
                                    Service service = mainSvrService.queryByID(task.getAdditional());
                                    if(service!=null)
                                    {
                                        SysFileTab sysFileTab = sysFileTabService.queryByID(service.getSysfilerecordid());
                                        if(sysFileTab!=null)
                                        {
                                            File operFile = new File(sysFileTab.getLocation());
                                            File operParentFile = operFile.getParentFile();
                                            if(operFile.exists())
                                            {
                                                operFile.delete();
                                            }

                                            if(operParentFile.exists()&&operParentFile.list()==null)
                                            {
                                                operParentFile.delete();
                                            }
                                        }

                                        sysFileTabService.deleteIfUnUse(sysFileTab.getId());

                                        mainSvrService.deleteFile(service);
                                    }

                                    message.setErrorMsg("操作成功！");
                                    message.setStatusCode(2000);
                                }else {

                                }
                            }
                        }else {
                            message.setStatusCode(5100);
                            message.setErrorMsg("不匹配的事务类型！");
                        }
                    }else{
                        message.setErrorMsg("不是当前用户事务！拒绝访问！");
                        message.setStatusCode(5200);
                    }
                }else{
                    message.setStatusCode(5300);
                    message.setErrorMsg("无效事务ID！");
                }
            }else response.sendRedirect("/index.html");
        }else{
            message.setErrorMsg("已被管理员禁止删除离线下载任务！");
            message.setStatusCode(5600);
        }

        return message;

    }

    @RequestMapping(value = "/api/getAllDownloadTasks")
    public List<tellStatus> getAllDownloadTasks(HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowGetAllDownloadTasks"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                List<Task> tasks = taskService.queryUserDownloadTask(user.getId());
                List<tellStatus> tellStatuses = new ArrayList<>();
                for(Task task : tasks)
                {
                    tellStatuses.add(getDownloadTask(task.getId(),session,response));
                }

                return tellStatuses;
            }else response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/getDownloadTask")
    public tellStatus getDownloadTask(@RequestParam("taskid") int taskid,HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowGetDownloadTask"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Task task = taskService.queryUserDownloadTaskByID(user.getId(), taskid);
                if(task!=null)
                {
                    if(Objects.equals(task.getUserid(), user.getId()))
                        return JSON.parseObject(Aria2Utils.tellStatus("http://localhost:6800/jsonrpc",task.getGid()),tellStatus.class);
                }
            }else response.sendRedirect("/index.html");
        }

        return null;
    }

//    public tellStatus BuildTellStatus(JSONObject object)
//    {
//        tellStatus r_tellStatus = new tellStatus();
//        r_tellStatus.setGid(object.getString("gid"));
//        r_tellStatus.setStatus(object.getString("status"));
//        r_tellStatus.setTotalLength(object.getBigInteger("totalLength"));
//        r_tellStatus.setCompletedLength(object.getBigInteger("completedLength"));
//        r_tellStatus.setUploadLength(object.getBigInteger("iploadLength"));
//        r_tellStatus.setBitfield(object.getByte("bitfield"));
//        r_tellStatus.setDownloadSpeed(object.getBigInteger("downloadSpeed"));
//        r_tellStatus.setUploadSpeed(object.getBigInteger("uploadSpeed"));
//        r_tellStatus.setInfoHash(object.getString("infoHash"));
//        r_tellStatus.setNumSeeders(object.getInteger("numSeeders"));
//        r_tellStatus.setSeeder(object.getBoolean("seeder"));
//        r_tellStatus.setPieceLength(object.getBigInteger("pieceLength"));
//        r_tellStatus.setNumPieces(object.getInteger("numPieces"));
//        r_tellStatus.setConnections(object.getInteger("connections"));
//        r_tellStatus.setErrorCode(object.getString("errorCode"));
//        r_tellStatus.setErrorMessage(object.getString("errorMessage");
//
//        return r_tellStatus;
//    }
}
