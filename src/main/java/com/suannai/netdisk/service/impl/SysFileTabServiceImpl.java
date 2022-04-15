package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.ServiceMapper;
import com.suannai.netdisk.mapper.SysFileTabMapper;
import com.suannai.netdisk.model.*;
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

    @Override
    public SysFileTab findByHash(String strHash) {
        SysFileTabExample example = new SysFileTabExample();
        SysFileTabExample.Criteria criteria = example.createCriteria();
        criteria.andFilehashEqualTo(strHash);

        List<SysFileTab> sysFileTabs = sysFileTabMapper.selectByExample(example);
        if(!sysFileTabs.isEmpty())
        {
            return sysFileTabs.get(0);
        }

        return null;
    }

    @Override
    public SysFileTab GetRoot() {
        SysFileTabExample example = new SysFileTabExample();
        SysFileTabExample.Criteria criteria = example.createCriteria();
        criteria.andRootmaskEqualTo(true);

        List<SysFileTab> sysFileTabs = sysFileTabMapper.selectByExample(example);
        if(!sysFileTabs.isEmpty())
        {
            return sysFileTabs.get(0);
        }

        return null;
    }

    @Override
    public SysFileTab queryByID(int ID) {
        return sysFileTabMapper.selectByPrimaryKey(ID);
    }

    @Override
    public SysFileTab findByLocation(String location) {
        SysFileTabExample example = new SysFileTabExample();
        SysFileTabExample.Criteria criteria = example.createCriteria();
        criteria.andLocationEqualTo(location);

        return sysFileTabMapper.selectByExample(example).get(0);
    }
}
