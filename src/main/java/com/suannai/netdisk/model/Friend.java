package com.suannai.netdisk.model;

import java.util.Date;

public class Friend {
    private Integer id;

    private Integer frdid;

    private Integer ownner;

    private String who;

    private Date date;

    public Friend(Integer id, Integer frdid, Integer ownner, String who, Date date) {
        this.id = id;
        this.frdid = frdid;
        this.ownner = ownner;
        this.who = who;
        this.date = date;
    }

    public Friend() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrdid() {
        return frdid;
    }

    public void setFrdid(Integer frdid) {
        this.frdid = frdid;
    }

    public Integer getOwnner() {
        return ownner;
    }

    public void setOwnner(Integer ownner) {
        this.ownner = ownner;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who == null ? null : who.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}