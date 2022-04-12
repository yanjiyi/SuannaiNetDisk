package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.SysConfigMapper;
import com.suannai.netdisk.model.SysConfig;
import com.suannai.netdisk.model.SysConfigExample;
import com.suannai.netdisk.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    SysConfigMapper sysConfigMapper;

    @Override
    public List<SysConfig> GetSysConfig() {
        return sysConfigMapper.selectByExample(new SysConfigExample());
    }
}
