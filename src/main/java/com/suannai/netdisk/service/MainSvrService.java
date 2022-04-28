package com.suannai.netdisk.service;

import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.User;

import java.util.List;

public interface MainSvrService {
    boolean blockUserFile(Service service);
    boolean deleteFile(Service service);
    boolean addFile(Service service);
    Service getUserDirRecord(User user,String realPath);
    List<Service> getChildren(User user,Service service);
    List<Service> getChildren(User user,String curPath);

    Service queryByID(int RecordID);

    boolean copyService(Service service,User user,Service workDir);

    List<Service> getWithSysFileTabID(int ID);

    Service queryUniqueService(Service service);

    boolean updateService(Service service);
}
