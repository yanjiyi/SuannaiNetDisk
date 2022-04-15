package com.suannai.netdisk.service;

import com.suannai.netdisk.model.SysConfig;

import java.util.List;

public interface SysConfigService {
    List<SysConfig> GetSysConfig();
    SysConfig GetConfig(String name);
    boolean ConfigIsAllow(String name);
}
