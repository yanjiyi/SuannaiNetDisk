package com.suannai.netdisk.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suannai.netdisk.dao.AddUriOption;
import com.suannai.netdisk.dao.OfficeTaskRespData;
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
import java.util.Date;
import java.util.UUID;

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

    @RequestMapping(value = "/addDownloadTask")
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

                    Task task = new Task();
                    task.setUserid(user.getId());
                    task.setTasktype(taskTypeService.GetTaskID("download"));
                    task.setTargetid(0);
                    task.setAdditional(-1);
                    task.setDate(new Date());
                    task.setTaskstatus(false);
                    task.setAria2id(jsonObject.getString("id"));
                    task.setIdle(false);
                    task.setGid(jsonObject.getString("result"));

                    taskService.createTask(task);
                    mainSvrService.addFile(service);
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
}
