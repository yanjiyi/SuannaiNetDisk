package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.SysConfig;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysConfigService;
import com.suannai.netdisk.service.SysFileTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
public class MainSvrController {
    @Autowired
    SysFileTabService sysFileTabService;

    @Autowired
    MainSvrService mainSvrService;

    @Autowired
    SysConfigService sysConfigService;

    @RequestMapping(value = "/listCur")
    public List<Service> listCur(HttpSession session,HttpServletResponse response) throws IOException {
        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(!sysConfigs.isEmpty())
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                for(SysConfig sysConfig : sysConfigs)
                {
                    if(sysConfig.getName().equals("AllowListCur")&&sysConfig.getValue().equals("YES"))
                    {
                        Service service = (Service) session.getAttribute("curService");
                        if(service==null)
                        {
                            String curDir = (String) session.getAttribute("CurWorkDir");
                            if(curDir!=null)
                            {
                                return mainSvrService.getChildren(user,curDir);
                            }
                        }else {
                            return mainSvrService.getChildren(user,service);
                        }
                    }
                }
            }else {
                response.sendRedirect("/index.html");
            }
        }

        return null;
    }

    @RequestMapping(value = "/pwd")
    public String GetPwd(HttpSession session,HttpServletResponse response) throws IOException {
        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(!sysConfigs.isEmpty())
        {
            for(SysConfig sysConfig : sysConfigs)
            {
                if(sysConfig.getName().equals("AllowPwd")&&sysConfig.getValue().equals("YES"))
                {
                    User user = (User) session.getAttribute("user");
                    if(user!=null)
                    {
                        return (String) session.getAttribute("CurWorkDir");
                    }else {
                        response.sendRedirect("/index.html");
                    }
                }
            }
        }

        return null;
    }

    @RequestMapping(value = "/getCurService")
    public Service GetCurService(HttpSession session,HttpServletResponse response) throws IOException {
        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(!sysConfigs.isEmpty())
        {
            for(SysConfig sysConfig : sysConfigs)
            {
                if(sysConfig.getName().equals("AllowGetCurService")&&sysConfig.getValue().equals("YES"))
                {
                    User user = (User) session.getAttribute("user");
                    if(user!=null)
                    {
                        Service service = (Service) session.getAttribute("curService");
                        if(service==null)
                        {
                            String curDir = (String) session.getAttribute("CurWorkDir");
                            if(curDir!=null)
                            {
                                Service service1 = mainSvrService.getUserDirRecord(user,curDir);
                                if(service1!=null)
                                {
                                    return service1;
                                }
                            }
                        }else {
                            return service;
                        }
                    }else {
                        response.sendRedirect("/index.html");
                    }
                }
            }
        }

        return null;
    }
    @RequestMapping(value = "/cd")
    public Message changeDir(@RequestParam("where") String where, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();

        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if (sysConfigs.isEmpty()) {
            message.setStatusCode(5500);
            message.setErrorMsg("已被管理员禁止切换工作目录！");
            return message;
        } else {
            User user = (User) session.getAttribute("user");

            for (SysConfig sysConfig : sysConfigs) {
                if (sysConfig.getName().equals("AllowCD") && sysConfig.getValue().equals("YES")) {
                    if (user != null) {
                        String CurDir = (String) session.getAttribute("CurWorkDir");
                        if (CurDir != null) {
                            if (!CurDir.equals("") && (CurDir.charAt(0) == '/')) {
                                Service CurService = mainSvrService.getUserDirRecord(user, CurDir + "/" + where);
                                if (CurService != null) {
                                    session.setAttribute("curService", CurService);
                                    session.setAttribute("CurWorkDir", CurDir + "/" + where);
                                    message.setErrorMsg("操作成功！");
                                    message.setStatusCode(2000);
                                    return message;
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
                    }
                } else {
                    response.sendRedirect("/index.html");
                }
            }
        }
        return message;
    }

    @RequestMapping(value = "/upload")
    public Message upload(@RequestParam("file") MultipartFile file, @RequestParam("fileHash") String filehash, @RequestParam("isDir") Boolean isDir, HttpSession session, HttpServletResponse response) throws IOException {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("上传文件");
        message.setStatusCode(2000);

        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if (sysConfigs.isEmpty()) {
            message.setErrorMsg("已被管理员禁止上传！");
            message.setStatusCode(5000);

        } else {
            for (SysConfig sysConfig : sysConfigs) {
                if (sysConfig.getName().equals("AllowUpload") && sysConfig.getValue().equals("YES")) {
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
                                service.setDirmask(isDir);
                                service.setParentid(currentWorkService.getId());
                                service.setSysfilerecordid(sysFileTab.getId());
                                service.setUploaddate(new Date());
                                service.setStatus(true);
                                service.setUserfilename(file.getOriginalFilename());
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
                                String uploadPath = null;
                                for (SysConfig subCfg : sysConfigs) {
                                    if (subCfg.getName().equals("uploadPath")) {
                                        uploadPath = subCfg.getValue();
                                    }
                                }

                                if (uploadPath != null) {
                                    if (!uploadPath.equals("")) {
                                        if (!isDir) {
                                            File dir = new File(uploadPath + file.getOriginalFilename());
                                            if (!dir.exists()) {
                                                dir.mkdirs();
                                            }

                                            file.transferTo(dir);

                                            SysFileTab uploadFileTab = new SysFileTab();
                                            uploadFileTab.setFilehash(filehash);
                                            uploadFileTab.setFilename(file.getOriginalFilename());
                                            uploadFileTab.setFilesize(file.getSize());
                                            uploadFileTab.setInuse(true);
                                            uploadFileTab.setLocation(uploadPath + file.getOriginalFilename());

                                            if (sysFileTabService.addRecord(uploadFileTab)) {
                                                Service userService = new Service();
                                                SysFileTab pointerRecord = sysFileTabService.findByHash(filehash);
                                                if (pointerRecord != null) {
                                                    userService.setUserid(user.getId());
                                                    userService.setStatus(true);
                                                    userService.setDirmask(false);
                                                    userService.setUserfilename(file.getOriginalFilename());
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
                                        }else {
                                            //只是目录我们添加用户记录即可
                                            Service userService = new Service();
                                            userService.setUserid(user.getId());
                                            userService.setStatus(true);
                                            userService.setDirmask(true);
                                            userService.setUserfilename(file.getOriginalFilename());
                                            userService.setUploaddate(new Date());
                                            userService.setSysfilerecordid(-1);
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
                }
            }

            message.setErrorMsg("已被管理员禁止上传！");
            message.setStatusCode(5000);
        }

        return message;
    }

    @RequestMapping(value = "/download")
    public void DownLoad(@RequestParam("recorid") int RecordID,HttpSession session,HttpServletResponse response) throws IOException {
        List<SysConfig> sysConfigs = sysConfigService.GetSysConfig();
        if(!sysConfigs.isEmpty())
        {
            for(SysConfig sysConfig : sysConfigs)
            {
                if(sysConfig.getName().equals("AllowDownload")&&sysConfig.getValue().equals("YES"))
                {
                    User user = (User) session.getAttribute("user");
                    if(user!=null)
                    {
                        Service service = mainSvrService.queryByID(RecordID);
                        if(service!=null)
                        {
                            //判断是否是文件件，打包ZIP下载，如果不是直接读取文件流发送
                            if(service.getDirmask())
                            {

                            }else {

                            }
                        }
                    }
                }
            }
        }

        response.sendRedirect("/error/DownLoadHasBeenDisable.html");
    }
}
