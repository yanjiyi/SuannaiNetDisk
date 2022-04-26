package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.LoginRequstData;
import com.suannai.netdisk.dao.Message;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysConfigService;
import com.suannai.netdisk.service.SysFileTabService;
import com.suannai.netdisk.service.UserService;
import com.suannai.netdisk.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MainSvrService mainSvrService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    SysFileTabService sysFileTabService;

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

    @RequestMapping(value = "/api/setUser")
    public Message setUser(@RequestParam("nickName") String nickName,HttpSession session,HttpServletResponse response) throws IOException {
        Message message = new Message();
        message.setStatusCode(2000);
        message.setErrorMsg("操作成功！");

        if(sysConfigService.ConfigIsAllow("AllowSetUser"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                user.setNickname(nickName);
                if(userService.updateUser(user))
                {
                    return message;
                }else {
                    message.setErrorMsg("操作失败！");
                    message.setStatusCode(4000);
                }
            }else response.sendRedirect("/index.html");
        }else {
            message.setStatusCode(5000);
            message.setErrorMsg("已被管理禁止改操作！");
        }

        return message;
    }

    @RequestMapping(value = "/api/setUserImg")
    public Message setUserImg(@RequestParam("ImgFile")MultipartFile imgFile,@RequestParam("fileHash") String filehash,@RequestParam("fileName") String fileName,HttpSession session,HttpServletResponse response) throws IOException {
        Message message = new Message();
        message.setStatusCode(2000);
        message.setErrorMsg("操作成功！");

        if(sysConfigService.ConfigIsAllow("AllowSetUserImg"))
        {
            User user = (User) session.getAttribute("user");
            if(user!=null)
            {
                if (sysConfigService.ConfigIsAllow("AllowUpload")) {
                    //Find User Root Service
                    Service currentWorkService = mainSvrService.getUserDirRecord(user, "/");
                    if(currentWorkService!=null)
                    {
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


                                //Success
                                Service insertedService = mainSvrService.queryUniqueService(service);
                                user.setImgserviceid(insertedService.getId());
                                if(userService.updateUser(user))
                                {
                                    message.setErrorMsg("操作成功！");
                                    message.setStatusCode(2000);
                                }else {
                                    message.setErrorMsg("操作失败！");
                                    message.setStatusCode(4000);
                                }
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
                                    File dir = new File(uploadPath + File.separator + imgFile.getOriginalFilename());
                                    System.out.println("Save To : " + dir.getPath());
                                    if (!dir.exists()) {
                                        dir.mkdirs();
                                    }

                                    imgFile.transferTo(dir);

                                    SysFileTab uploadFileTab = new SysFileTab();
                                    uploadFileTab.setFilehash(filehash);
                                    uploadFileTab.setFilename(fileName);
                                    uploadFileTab.setFilesize(imgFile.getSize());
                                    uploadFileTab.setInuse(true);
                                    uploadFileTab.setLocation(uploadPath + File.separator + imgFile.getOriginalFilename());
                                    uploadFileTab.setRootmask(false);

                                    if (sysFileTabService.addRecord(uploadFileTab)) {
                                        Service userimgService = new Service();
                                        SysFileTab pointerRecord = sysFileTabService.findByHash(filehash);
                                        if (pointerRecord != null) {
                                            userimgService.setUserid(user.getId());
                                            userimgService.setStatus(true);
                                            userimgService.setDirmask(false);
                                            userimgService.setUserfilename(fileName);
                                            userimgService.setUploaddate(new Date());
                                            userimgService.setSysfilerecordid(pointerRecord.getId());
                                            userimgService.setParentid(currentWorkService.getId());

                                            if (mainSvrService.addFile(userimgService)) {
                                                //Success
                                                Service insertedService = mainSvrService.queryUniqueService(userimgService);
                                                user.setImgserviceid(insertedService.getId());
                                                if(userService.updateUser(user))
                                                {
                                                    message.setErrorMsg("操作成功！");
                                                    message.setStatusCode(2000);
                                                }else {
                                                    message.setErrorMsg("操作失败！");
                                                    message.setStatusCode(4000);
                                                }
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
                    }else {
                        message.setStatusCode(5100);
                        message.setErrorMsg("无法找到用户根目录记录！");
                    }

                }else{
                    message.setStatusCode(5000);
                    message.setErrorMsg("已被管理禁止上传文件！");
                }
            }else response.sendRedirect("/index.html");
        }else {
            message.setStatusCode(5000);
            message.setErrorMsg("已被管理禁止改操作！");
        }
        return message;
    }
}
