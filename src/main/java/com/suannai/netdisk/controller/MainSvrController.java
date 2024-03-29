package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.*;
import com.suannai.netdisk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class MainSvrController {
    @Autowired
    SysFileTabService sysFileTabService;

    @Autowired
    MainSvrService mainSvrService;

    @Autowired
    TaskTypeService taskTypeService;

    @Autowired
    TaskService taskService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/listCur")
    public List<Service> listCur(HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (sysConfigService.ConfigIsAllow("AllowListCur")) {
                Service service = (Service) session.getAttribute("curService");
                if (service == null) {
                    String curDir = (String) session.getAttribute("CurWorkDir");
                    if (curDir != null) {
//                        return mainSvrService.getChildren(user, curDir);
                        List<Service> children = mainSvrService.getChildren(user, curDir);
                        for(int i=0;i<children.size();i++)
                        {
                            if(Objects.equals(user.getImgserviceid(), children.get(i).getId()))
                            {
                                children.remove(i);
                            }
                        }

                        return children;
                    }
                } else {
//                    return mainSvrService.getChildren(user, service);
                    List<Service> children = mainSvrService.getChildren(user, service);
                    for(int i=0;i<children.size();i++)
                    {
                        if(Objects.equals(user.getImgserviceid(), children.get(i).getId()))
                        {
                            children.remove(i);
                        }
                    }

                    return children;
                }
            }
        } else {
            response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/pwd")
    public String GetPwd(HttpSession session, HttpServletResponse response) throws IOException {
        if (sysConfigService.ConfigIsAllow("AllowPwd")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return (String) session.getAttribute("CurWorkDir");
            } else {
                response.sendRedirect("/index.html");
            }
        }
        return null;
    }


    @RequestMapping(value = "/api/getCurService")
    public Service GetCurService(HttpSession session, HttpServletResponse response) throws IOException {

        if (sysConfigService.ConfigIsAllow("AllowGetCurService")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Service service = (Service) session.getAttribute("curService");
                if (service == null) {
                    String curDir = (String) session.getAttribute("CurWorkDir");
                    if (curDir != null) {
                        Service service1 = mainSvrService.getUserDirRecord(user, curDir);
                        if (service1 != null) {
                            return service1;
                        }
                    }
                } else {
                    return service;
                }
            } else {
                response.sendRedirect("/index.html");
            }
        }

        return null;
    }


    @RequestMapping(value = "/api/cd")
    public Message changeDir(@RequestParam("where") String where, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();
        User user = (User) session.getAttribute("user");

        if (sysConfigService.ConfigIsAllow("AllowCD")) {
            if (user != null) {
                String CurDir = (String) session.getAttribute("CurWorkDir");
                if (CurDir != null) {
                    if (!CurDir.equals("") && (CurDir.charAt(0) == '/')) {
                        Service CurService = mainSvrService.getUserDirRecord(user, where);
                        if (CurService != null) {
                            if(CurService.getDirmask())
                            {
                                session.setAttribute("curService", CurService);

                                session.setAttribute("CurWorkDir", where);
                                message.setErrorMsg("操作成功！");
                                message.setStatusCode(2000);
                                return message;
                            }
                            else{
                                message.setStatusCode(6000);
                                message.setErrorMsg("不是目录！");
                                return message;
                            }
                        } else {
                            message.setErrorMsg("操作失败！");
                            message.setStatusCode(5100);
                            return message;
                        }
                    } else {
                        message.setStatusCode(5200);
                        message.setErrorMsg("当前非法路径！");
                        return message;
                    }
                } else {
                    Service CurService = mainSvrService.getUserDirRecord(user, "/" + where);
                    if (CurService != null) {
                        session.setAttribute("curService", CurService);
                        session.setAttribute("CurWorkDir", "/" + where);
                        message.setErrorMsg("操作成功！");
                        message.setStatusCode(2000);
                        return message;
                    } else {
                        message.setErrorMsg("操作失败！");
                        message.setStatusCode(5100);
                        return message;
                    }
                }
            } else {
                response.sendRedirect("/index.html");
            }
        } else {
            message.setStatusCode(5500);
            message.setErrorMsg("已被管理禁止CD！");
        }
        return message;
    }

    @RequestMapping(value = "/api/checkFile")
    public Message checkFile(@RequestParam("fileHash") String filehash,@RequestParam("fileName") String fileName,HttpSession session,HttpServletResponse response) throws IOException {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("上传文件");
        message.setStatusCode(2000);


        if (sysConfigService.ConfigIsAllow("AllowUpload")) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                String currentWorkDir = (String) session.getAttribute("CurWorkDir");
                if (currentWorkDir == null) {
                    currentWorkDir = "/";
                    session.setAttribute("CurWorkDir", currentWorkDir);
                } else {
                    if (currentWorkDir.charAt(0) != '/') {
                        message.setErrorMsg("5500");
                        message.setErrorMsg("非法工作路径！");
                        return message;
                    }
                }

                //Find Current Work Service

//                        Service currentWorkService = mainSvrService.getUserDirRecord(user,currentWorkDir);
                Service currentWorkService = (Service) session.getAttribute("curService");
                if (currentWorkService == null) {
                    currentWorkService = mainSvrService.getUserDirRecord(user, currentWorkDir);
                }

                if (currentWorkService != null) {
                    SysFileTab sysFileTab = sysFileTabService.findByHash(filehash);
                    if (sysFileTab != null) {
                        Service service = new Service();
                        service.setDirmask(false);
                        service.setParentid(currentWorkService.getId());
                        service.setSysfilerecordid(sysFileTab.getId());
                        service.setUploaddate(new Date());
                        service.setStatus(true);
                        service.setUserfilename(fileName);
                        service.setUserid(user.getId());

                        if (mainSvrService.addFile(service)) {
                            message.setErrorMsg("秒传成功！");
                            message.setStatusCode(2000);
                            return message;
                        } else {
                            message.setErrorMsg("添加用户记录失败！");
                            message.setStatusCode(5200);
                        }
                    }else{
                        message.setStatusCode(3300);
                        message.setErrorMsg("需要上传！");
                    }
                }else {
                    message.setStatusCode(5600);
                    message.setErrorMsg("无法找当前用户文件夹记录！请检查工作路径是否正确！");
                    return message;
                }
            }else {
                response.sendRedirect("/index.html");
            }
        } else {
            message.setErrorMsg("已被管理员禁止上传！");
            message.setStatusCode(5000);
        }

        return message;
    }
    @RequestMapping(value = "/api/upload")
    public Message upload(@RequestParam("file") MultipartFile file, @RequestParam("fileHash") String filehash, @RequestParam("isDir") Boolean isDir, @RequestParam("dirName") String dirname,@RequestParam("fileName") String fileName, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("上传文件");
        message.setStatusCode(2000);


        if (sysConfigService.ConfigIsAllow("AllowUpload")) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                String currentWorkDir = (String) session.getAttribute("CurWorkDir");
                if (currentWorkDir == null) {
                    currentWorkDir = "/";
                    session.setAttribute("CurWorkDir", currentWorkDir);
                } else {
                    if (currentWorkDir.charAt(0) != '/') {
                        message.setErrorMsg("5500");
                        message.setErrorMsg("非法工作路径！");
                        return message;
                    }
                }

                //Find Current Work Service

//                        Service currentWorkService = mainSvrService.getUserDirRecord(user,currentWorkDir);
                Service currentWorkService = (Service) session.getAttribute("curService");
                if (currentWorkService == null) {
                    currentWorkService = mainSvrService.getUserDirRecord(user, currentWorkDir);
                }

                if (currentWorkService != null) {
                    if (isDir) {
                        //只是目录我们添加用户记录即可
                        Service userService = new Service();
                        userService.setUserid(user.getId());
                        userService.setStatus(true);
                        userService.setDirmask(true);
                        userService.setUserfilename(dirname);
                        userService.setUploaddate(new Date());
                        userService.setSysfilerecordid(-1);
                        userService.setParentid(currentWorkService.getId());

                        if (mainSvrService.addFile(userService)) {
                            message.setStatusCode(2000);
                            message.setErrorMsg("上传成功");
                            return message;
                        }
                        message.setStatusCode(5400);
                        message.setErrorMsg("无法添加用户记录！");
                        return message;

                    }

                    SysFileTab sysFileTab = sysFileTabService.findByHash(filehash);
                    if (sysFileTab != null) {
                        Service service = new Service();
                        service.setDirmask(false);
                        service.setParentid(currentWorkService.getId());
                        service.setSysfilerecordid(sysFileTab.getId());
                        service.setUploaddate(new Date());
                        service.setStatus(true);
                        service.setUserfilename(fileName);
                        service.setUserid(user.getId());

                        if (mainSvrService.addFile(service)) {
                            message.setErrorMsg("秒传成功！");
                            message.setStatusCode(2000);
                            return message;
                        } else {
                            message.setErrorMsg("添加用户记录失败！");
                            message.setStatusCode(5200);
                        }
                    } else {
                        //上传文件 并添加用户记录
                        String uploadPath = sysConfigService.GetConfig("uploadPath").getValue();

                        if (uploadPath != null) {
                            if (!uploadPath.equals("")) {
                                UUID uuid = UUID.randomUUID();
                                File dir = new File(uploadPath + File.separator + filehash);
                                System.out.println("Save To : " + dir.getPath());
                                if (!dir.exists()) {
                                    dir.mkdirs();
                                }

                                if(file.getSize()==0)
                                {
                                    dir.createNewFile();
                                }else{
                                    file.transferTo(dir);
                                }

                                SysFileTab uploadFileTab = new SysFileTab();
                                uploadFileTab.setFilehash(filehash);
                                uploadFileTab.setFilename(fileName);
                                uploadFileTab.setFilesize(file.getSize());
                                uploadFileTab.setInuse(true);
                                uploadFileTab.setLocation(uploadPath + File.separator +  filehash);
                                uploadFileTab.setRootmask(false);

                                if (sysFileTabService.addRecord(uploadFileTab)) {
                                    Service userService = new Service();
                                    SysFileTab pointerRecord = sysFileTabService.findByHash(filehash);
                                    if (pointerRecord != null) {
                                        userService.setUserid(user.getId());
                                        userService.setStatus(true);
                                        userService.setDirmask(false);
                                        userService.setUserfilename(fileName);
                                        userService.setUploaddate(new Date());
                                        userService.setSysfilerecordid(pointerRecord.getId());
                                        userService.setParentid(currentWorkService.getId());

                                        if (mainSvrService.addFile(userService)) {
                                            message.setStatusCode(2000);
                                            message.setErrorMsg("上传成功");
                                            return message;
                                        } else {
                                            message.setStatusCode(5400);
                                            message.setErrorMsg("无法添加用户记录！");
                                            return message;
                                        }
                                    } else {
                                        message.setStatusCode(5700);
                                        message.setErrorMsg("无法操作系统文件表");
                                        return message;
                                    }
                                }
                            }

                        } else {
                            message.setStatusCode(5300);
                            message.setErrorMsg("管理员未配置上传存储路径！无法上传文件！");
                            return message;
                        }
                    }
                } else {
                    message.setStatusCode(5600);
                    message.setErrorMsg("无法找当前用户文件夹记录！请检查工作路径是否正确！");
                    return message;
                }
            } else {
                response.sendRedirect("/index.html");
            }
        } else {
            message.setErrorMsg("已被管理员禁止上传！");
            message.setStatusCode(5000);
        }

        return message;
    }


    @RequestMapping(value = "/api/download")
    public void DownLoad(@RequestParam("recorid") int RecordID, HttpSession session, HttpServletResponse response) throws IOException {

        if (sysConfigService.ConfigIsAllow("AllowDownload")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Service service = mainSvrService.queryByID(RecordID);
                if (service != null) {
                    if (service.getDirmask()) {
                        //判断是否是文件件，打包ZIP下载，如果不是直接读取文件流发送
                        String filename = service.getUserfilename() + ".zip";
                        filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
                        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
                        response.setContentType("multipart/form-data");
                        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
                        ProcessService(user, zipOutputStream, service, "");
                        zipOutputStream.close();
                    } else {
                        service.setUserfilename(URLEncoder.encode(service.getUserfilename(), StandardCharsets.UTF_8));

                        File file = new File(sysFileTabService.queryByID(service.getSysfilerecordid()).getLocation());
                        InputStream bis = new BufferedInputStream(new FileInputStream(file));

                        response.setHeader("Content-Disposition", "attachment;filename=" + service.getUserfilename());
                        response.setContentType("multipart/form-data");
                        response.setContentLength(bis.available());

                        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while ((len = bis.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, len);
                            outputStream.flush();
                        }

                        bis.close();
                        outputStream.close();
                    }

                    return;
                }
            } else {
                response.sendRedirect("/index.html");
            }
        }
        response.sendRedirect("/error/DownLoadHasBeenDisable.html");
    }


    protected void ProcessService(User user, ZipOutputStream zipOutputStream, Service targets, String base) throws IOException {
        List<Service> curContext = mainSvrService.getChildren(user, targets);

        for (Service curTarget : curContext) {
            if (!curTarget.getDirmask()) {
                if(sysFileTabService.queryByID(curTarget.getSysfilerecordid()).getFilesize()>0)
                {
                    FileInputStream fis = new FileInputStream(sysFileTabService.queryByID(curTarget.getSysfilerecordid()).getLocation());
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    zipOutputStream.putNextEntry(new ZipEntry(base + curTarget.getUserfilename()));
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = bis.read(buf, 0, 1024)) != -1) {
                        zipOutputStream.write(buf, 0, len);
                    }

                    bis.close();
                    fis.close();
                }else {
                    zipOutputStream.putNextEntry(new ZipEntry(base + curTarget.getUserfilename()));
                }

            } else {
                List<Service> dirContext = mainSvrService.getChildren(user, curTarget);
                if (dirContext.isEmpty()) {
                    zipOutputStream.putNextEntry(new ZipEntry(base +  curTarget.getUserfilename() + File.separator));
                } else {
                    ProcessService(user, zipOutputStream, curTarget, base +  curTarget.getUserfilename() + File.separator);
                }
            }
        }
    }

    @RequestMapping(value = "/api/share")
    public Message Shader(@RequestParam("id") int id, @RequestParam("toWho") int who,HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();

        if (sysConfigService.ConfigIsAllow("AllowShare")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int shareType = taskTypeService.GetTaskID("Share");
                if (shareType > 0) {

                } else {
                    message.setStatusCode(5100);
                    message.setErrorMsg("无效事务类型！");
                }

                Service service = mainSvrService.queryByID(id);
                User friend = userService.QueryByID(who);
                if(service==null||friend==null)
                {
                    message.setStatusCode(5900);
                    message.setErrorMsg("无效参数！");
                    return message;
                }

                Task task = new Task();
                task.setIdle(true);
                task.setTaskstatus(false);
                task.setUserid(user.getId());
                task.setAdditional(service.getId());
                task.setDate(new Date());
                task.setTargetid(friend.getId());
                task.setTasktype(shareType);

                if (taskService.createTask(task)) {
                    message.setStatusCode(2000);
                    message.setErrorMsg("发起分享成功！");
                } else {
                    message.setStatusCode(5200);
                    message.setErrorMsg("发起分享失败！");
                }
            } else {
                response.sendRedirect("/index.html");
            }
        } else {
            message.setErrorMsg("已被管理员禁止分享功能！");
            message.setStatusCode(5500);
        }

        return message;
    }

    @RequestMapping(value = "/api/acceptShare")
    public Message acceptShare(@RequestParam("taskid") int taskid, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();


        if (sysConfigService.ConfigIsAllow("AllowAcceptShare")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Task task = taskService.queryByID(taskid);
                if (task == null) {
                    message.setErrorMsg("无效事务ID");
                    message.setStatusCode(5800);
                    return message;
                }

                if (!Objects.equals(task.getTargetid(), user.getId())) {
                    message.setStatusCode(5200);
                    message.setErrorMsg("不是当前用户事务！拒绝访问！");
                    return message;
                }

                if (task.getTasktype() == taskTypeService.GetTaskID("Share")) {
                    Service service = mainSvrService.queryByID(task.getAdditional());
                    if (service != null) {
                        Service curWorkDir = (Service) session.getAttribute("curService");
                        if (curWorkDir != null) {
                            if (mainSvrService.copyService(service, user, curWorkDir)) {
                                message.setStatusCode(2000);
                                message.setErrorMsg("接受成功！");

                                task.setTaskstatus(true);
                                taskService.updateTask(task);
//                                        task.setIdle(false);
                                taskService.lockTask(task);

                                return message;

                            } else {
                                message.setStatusCode(5700);
                                message.setErrorMsg("更新用户记录失败！");
                                return message;
                            }
                        } else {
                            curWorkDir = mainSvrService.getUserDirRecord(user, (String) session.getAttribute("CurWorkDir"));
                            if (curWorkDir != null) {
                                if (mainSvrService.copyService(service, user, curWorkDir)) {
                                    if (mainSvrService.copyService(service, user, curWorkDir)) {
                                        message.setStatusCode(2000);
                                        message.setErrorMsg("接受成功！");

                                        task.setTaskstatus(true);
                                        taskService.updateTask(task);
//                                              task.setIdle(false);
                                        taskService.lockTask(task);

                                        return message;

                                    } else {
                                        message.setStatusCode(5700);
                                        message.setErrorMsg("更新用户记录失败！");
                                        return message;
                                    }
                                } else {

                                }
                            } else {
                                message.setErrorMsg("获取当前工作目录Service失败！");
                                message.setStatusCode(5600);
                            }
                        }
                    } else {
                        message.setStatusCode(5400);
                        message.setErrorMsg("无法知道附属Service！");
                        return message;
                    }
                } else {
                    message.setStatusCode(5300);
                    message.setErrorMsg("不匹配的事务类型！");
                    return message;
                }
            } else response.sendRedirect("/index.html");
        } else {

            message.setStatusCode(5500);
            message.setErrorMsg("已被管理员禁止接受分享功能！");
        }
        return message;
    }

    @RequestMapping(value = "/api/refuseShare")
    public Message refuseShare(@RequestParam("taskid") int taskid, HttpServletResponse response, HttpSession session) throws IOException {
        Message message = new Message();


        if (sysConfigService.ConfigIsAllow("AllowRefuseShare")) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Task task = taskService.queryByID(taskid);
                if (task != null) {
                    if (Objects.equals(task.getTargetid(), user.getId())) {
                        if (task.getTasktype() != taskTypeService.GetTaskID("Share")) {
                            message.setErrorMsg("不匹配的事务类型！");
                            message.setStatusCode(5300);
                        } else {
                            taskService.lockTask(task);
                            message.setStatusCode(2000);
                            message.setErrorMsg("拒绝成功！");
                            return message;
                        }
                    } else {
                        message.setStatusCode(5200);
                        message.setErrorMsg("不是当前用户事务！拒绝访问！");
                    }
                } else {
                    message.setStatusCode(5100);
                    message.setErrorMsg("无效事务ID");
                }
            } else response.sendRedirect("/index.html");
        } else {
            message.setErrorMsg("已被管理员禁止拒绝分享功能！");
            message.setStatusCode(5500);
        }

        return message;
    }

    @RequestMapping(value = "/api/querySysFileTab")
    public SysFileTab querySysFileTab(@RequestParam("id") int id,HttpSession session,HttpServletResponse response) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowQuerySysFileTab"))
        {
            System.out.println("ID : "+id);
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                return sysFileTabService.queryByID(id);
            }else response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/queryService")
    public Service queryService(@RequestParam("id") int id, HttpServletResponse response, HttpSession session) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowQueryService"))
        {
            System.out.println("ID : "+id);
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Service result = mainSvrService.queryByID(id);
                if(result!=null)
                {
                    if(Objects.equals(result.getUserid(), user.getId()))
                    {
                        return result;
                    }
                }
            }
            else response.sendRedirect("/index.html");
        }

        return null;
    }

    @RequestMapping(value = "/api/deleteService")
    public void deleteService(@RequestParam("id") int id,HttpServletResponse response,HttpSession session) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowDeleteService"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Service service = mainSvrService.queryByID(id);
                if(service!=null)
                {
                    deleteServiceCall(service,user);
                }
            }else response.sendRedirect("/index.html");
        }
    }

    protected void deleteServiceCall(Service service,User user)
    {
        if(service.getDirmask())
        {
            List<Service> subs = mainSvrService.getChildren(user,service);
            for(Service sub : subs)
            {
                deleteServiceCall(sub,user);
            }
        }

        mainSvrService.deleteFile(service);
    }

    @RequestMapping(value = "/api/moveTo")
    public String moveTo(@RequestParam("target") int target,@RequestParam("where") int where,HttpServletResponse response,HttpSession session) throws IOException {
        if(sysConfigService.ConfigIsAllow("AllowMoveTo"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                Service targetSvr = mainSvrService.queryByID(target);
                Service whereSvr = null;
                if(where>0)
                    whereSvr = mainSvrService.queryByID(where);
                else
                    whereSvr = mainSvrService.GetUserRoot(user);

                if(targetSvr!=null&&whereSvr!=null)
                {
                    targetSvr.setParentid(whereSvr.getId());
                    if(mainSvrService.updateService(targetSvr))
                    {
                        return "移动成功！";
                    }else {
                        return  "移动失败！更新数据库记录失败！";
                    }
                }else {
                    return "被移动目标或移动目的地无效！";
                }
            }else response.sendRedirect("/index.html");
        }

        return "管理员禁止用户移动文件或文件夹位置！";
    }
}
