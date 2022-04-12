package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.ServiceMapper;
import com.suannai.netdisk.mapper.SysFileTabMapper;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.ServiceExample;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.service.SysFileTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class SysFileTabServiceImpl implements SysFileTabService {

    @Autowired
    ServiceMapper serviceMapper;

    @Autowired
    SysFileTabMapper sysFileTabMapper;

    @Override
    public boolean deleteIfUnUse(int RecordID) {
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andSysfilerecordidEqualTo(RecordID);

        List<Service> services = serviceMapper.selectByExample(example);
        if(services.isEmpty())
        {
            SysFileTab sysFileTab = sysFileTabMapper.selectByPrimaryKey(RecordID);
            File file = new File(sysFileTab.getLocation());
            if(file.delete())
            {
                System.out.println("System Record Delete FileSystem Object : " + sysFileTab.getLocation());
            }

            return sysFileTabMapper.deleteByPrimaryKey(RecordID) == 1;
        }
        return false;
    }

    @Override
    public boolean addRecord(SysFileTab sysFileTab) {
        return sysFileTabMapper.insert(sysFileTab) == 1;
    }
}
