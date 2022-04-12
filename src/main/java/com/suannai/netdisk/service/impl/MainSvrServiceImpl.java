package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.ServiceMapper;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysFileTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainSvrServiceImpl implements MainSvrService {
    @Autowired
    SysFileTabService sysFileTabService;

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public boolean blockUserFile(Service service) {
        service.setStatus(false);
        return serviceMapper.updateByPrimaryKey(service) == 1;
    }

    @Override
    public boolean deleteFile(Service service) {
        if(sysFileTabService.deleteIfUnUse(service.getSysfilerecordid()))
        {
            System.out.println("System File RecordID Has Been Erase : " + service.getSysfilerecordid());
        }

        return serviceMapper.deleteByPrimaryKey(service.getId()) == 1;
    }

    @Override
    public boolean addFile(Service service) {
        return serviceMapper.insert(service) == 1;
    }
}
