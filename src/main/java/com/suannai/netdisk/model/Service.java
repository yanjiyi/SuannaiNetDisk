package com.suannai.netdisk.model;

import java.util.Date;

public class Service {
    private Integer id;

    private Integer userid;

    private String userfilename;

    private Integer sysfilerecordid;

    private Boolean status;

    private Date uploaddate;

    public Service(Integer id, Integer userid, String userfilename, Integer sysfilerecordid, Boolean status, Date uploaddate) {
        this.id = id;
        this.userid = userid;
        this.userfilename = userfilename;
        this.sysfilerecordid = sysfilerecordid;
        this.status = status;
        this.uploaddate = uploaddate;
    }

    public Service() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserfilename() {
        return userfilename;
    }

    public void setUserfilename(String userfilename) {
        this.userfilename = userfilename == null ? null : userfilename.trim();
    }

    public Integer getSysfilerecordid() {
        return sysfilerecordid;
    }

    public void setSysfilerecordid(Integer sysfilerecordid) {
        this.sysfilerecordid = sysfilerecordid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }
}