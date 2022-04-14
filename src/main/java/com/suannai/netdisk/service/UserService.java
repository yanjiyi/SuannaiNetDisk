package com.suannai.netdisk.service;

import com.suannai.netdisk.model.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    boolean createUser(User user);
    boolean login(User user, String strIP, HttpSession session);
    boolean blockUser(User user);

    boolean deleteUser(User user);
    User QueryByID(int ID);
}
