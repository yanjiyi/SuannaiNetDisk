package com.suannai.netdisk.service;

import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.User;

public interface MainSvrService {
    boolean blockUserFile(Service service);
    boolean deleteFile(Service service);
    boolean addFile(Service service);

    Service getUserDirRecord(User user,String realPath);
}
