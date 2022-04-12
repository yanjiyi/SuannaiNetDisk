package com.suannai.netdisk.service;

import com.suannai.netdisk.model.Service;

public interface MainSvrService {
    boolean blockUserFile(Service service);
    boolean deleteFile(Service service);
    boolean addFile(Service service);
}
