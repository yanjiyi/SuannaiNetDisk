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

    @Override
    public SysConfig GetConfig(String name) {
        SysConfigExample example = new SysConfigExample();
        SysConfigExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);

        return sysConfigMapper.selectByExample(example).get(0);
    }

    @Override
    public boolean ConfigIsAllow(String name) {
        SysConfigExample example = new SysConfigExample();
        SysConfigExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);

        SysConfig config = sysConfigMapper.selectByExample(example).get(0);
        if(config!=null)
        {
            return config.getValue().equals("YES");
        }

        return false;
    }
}
