package com.suannai.netdisk.service;

import com.suannai.netdisk.model.SysFileTab;

public interface SysFileTabService {
    boolean deleteIfUnUse(int RecordID);
    boolean addRecord(SysFileTab sysFileTab);
    SysFileTab findByHash(String strHash);
    SysFileTab GetRoot();

    SysFileTab queryByID(int ID);

    SysFileTab findByLocation(String location);
}
