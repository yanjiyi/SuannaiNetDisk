package com.suannai.netdisk.model;

import java.util.Date;

public class Task {
    private Integer id;

    private Integer tasktype;

    private Date date;

    private Integer userid;

    private Integer targetid;

    private Boolean taskstatus;

    private Integer additional;

    private Boolean idle;

    private String gid;

    private String aria2id;

    public Task(Integer id, Integer tasktype, Date date, Integer userid, Integer targetid, Boolean taskstatus, Integer additional, Boolean idle, String gid, String aria2id) {
        this.id = id;
        this.tasktype = tasktype;
        this.date = date;
        this.userid = userid;
        this.targetid = targetid;
        this.taskstatus = taskstatus;
        this.additional = additional;
        this.idle = idle;
        this.gid = gid;
        this.aria2id = aria2id;
    }

    public Task() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTasktype() {
        return tasktype;
    }

    public void setTasktype(Integer tasktype) {
        this.tasktype = tasktype;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

    public Boolean getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Boolean taskstatus) {
        this.taskstatus = taskstatus;
    }

    public Integer getAdditional() {
        return additional;
    }

    public void setAdditional(Integer additional) {
        this.additional = additional;
    }

    public Boolean getIdle() {
        return idle;
    }

    public void setIdle(Boolean idle) {
        this.idle = idle;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getAria2id() {
        return aria2id;
    }

    public void setAria2id(String aria2id) {
        this.aria2id = aria2id == null ? null : aria2id.trim();
    }
}