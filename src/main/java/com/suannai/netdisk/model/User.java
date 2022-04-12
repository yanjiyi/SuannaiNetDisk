package com.suannai.netdisk.model;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private Date lastlogintime;

    private String lastloginip;

    private Boolean status;

    public User(Integer id, String username, String password, Date lastlogintime, String lastloginip, Boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastlogintime = lastlogintime;
        this.lastloginip = lastloginip;
        this.status = status;
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
}