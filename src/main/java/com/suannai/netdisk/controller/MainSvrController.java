package com.suannai.netdisk.controller;

import com.suannai.netdisk.dao.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class MainSvrController {
    @RequestMapping(value = "/upload")
    public Message upload(@RequestParam("file")MultipartFile file, HttpSession session)
    {
        Message message = new Message();
        message.setErrorMsg("操作成功！");
        message.setOperation("上传文件");
        message.setStatusCode(2000);

        return message;
    }
}
