package com.suannai.netdisk.dao;

import com.suannai.netdisk.model.Service;

public class ShareRequestData {
    Service service;
    int userid;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


}
