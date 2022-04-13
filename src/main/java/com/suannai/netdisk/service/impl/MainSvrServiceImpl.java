package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.ServiceMapper;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.ServiceExample;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysFileTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public Service getUserDirRecord(User user, String realPath) {
        if(realPath.charAt(0)=='/') {
            List<String> dirList = GetDirList(realPath);
            SysFileTab rootRecord = sysFileTabService.GetRoot();
            if (rootRecord != null) {
                Service rootService = null;

                ServiceExample findRootExam = new ServiceExample();
                ServiceExample.Criteria criteria1 = findRootExam.createCriteria();
                criteria1.andSysfilerecordidEqualTo(rootRecord.getId());

                List<Service> services = serviceMapper.selectByExample(findRootExam);
                if (!services.isEmpty()) {
                    rootService = services.get(0);
                } else {
                    //Need Create User Root Record
                    return null;
                }

                Service curService = rootService;
                for (String cur : dirList) {
                    ServiceExample example = new ServiceExample();
                    ServiceExample.Criteria criteria = example.createCriteria();
                    criteria.andDirmaskEqualTo(true);
                    criteria.andUseridEqualTo(user.getId());
                    criteria.andUserfilenameEqualTo(cur);
                    criteria.andParentidEqualTo(curService.getId());

                    List<Service> result = serviceMapper.selectByExample(example);
                    if(!result.isEmpty())
                    {
                        curService = result.get(0);
                    }else {
                        return null;
                    }
                }

                return curService;
            }
        }
        return null;
    }

    @Override
    public List<Service> getChildren(User user, Service service) {
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getId());
        criteria.andParentidEqualTo(service.getId());

        return serviceMapper.selectByExample(example);
    }

    @Override
    public List<Service> getChildren(User user, String curPath) {
        Service userDirRecord = getUserDirRecord(user, curPath);

        if(userDirRecord==null)
            return null;

        return getChildren(user,userDirRecord);
    }

    protected List<String> GetDirList(String Path)
    {
        String[] list = Path.split("/");
        List<String> result = new ArrayList<>();
        Collections.addAll(result, list);

        return result;
    }
}
