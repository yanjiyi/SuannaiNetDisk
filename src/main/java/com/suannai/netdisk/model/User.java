package com.suannai.netdisk.model;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private Date lastlogintime;

    private String lastloginip;

    private Boolean status;

    private Integer imgserviceid;

    private String nickname;

    public User(Integer id, String username, String password, Date lastlogintime, String lastloginip, Boolean status, Integer imgserviceid, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastlogintime = lastlogintime;
        this.lastloginip = lastloginip;
        this.status = status;
        this.imgserviceid = imgserviceid;
        this.nickname = nickname;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getImgserviceid() {
        return imgserviceid;
    }

    public void setImgserviceid(Integer imgserviceid) {
        this.imgserviceid = imgserviceid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
}