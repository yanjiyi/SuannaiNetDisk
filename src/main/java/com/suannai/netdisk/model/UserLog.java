package com.suannai.netdisk.model;

import java.util.Date;

public class UserLog {
    private Integer id;

    private Integer userid;

    private Boolean superuser;

    private String operationdescription;

    private Date date;

    public UserLog(Integer id, Integer userid, Boolean superuser, String operationdescription, Date date) {
        this.id = id;
        this.userid = userid;
        this.superuser = superuser;
        this.operationdescription = operationdescription;
        this.date = date;
    }

    public UserLog() {
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

    public Boolean getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
    }

    public String getOperationdescription() {
        return operationdescription;
    }

    public void setOperationdescription(String operationdescription) {
        this.operationdescription = operationdescription == null ? null : operationdescription.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}