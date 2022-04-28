package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.ServiceMapper;
import com.suannai.netdisk.model.Service;
import com.suannai.netdisk.model.ServiceExample;
import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.MainSvrService;
import com.suannai.netdisk.service.SysFileTabService;
import com.suannai.netdisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class MainSvrServiceImpl implements MainSvrService {
    @Autowired
    SysFileTabService sysFileTabService;

    @Autowired
    ServiceMapper serviceMapper;

    @Autowired
    UserService userService;

    @Override
    public boolean blockUserFile(Service service) {
        service.setStatus(false);
        return serviceMapper.updateByPrimaryKey(service) == 1;
    }

    @Override
    public boolean deleteFile(Service service) {
        boolean deleted = serviceMapper.deleteByPrimaryKey(service.getId()) == 1;

        if(sysFileTabService.deleteIfUnUse(service.getSysfilerecordid()))
        {
            System.out.println("System File RecordID Has Been Erase : " + service.getSysfilerecordid());
        }

        return deleted;
    }

    @Override
    public boolean addFile(Service service) {
        return serviceMapper.insert(service) == 1;
    }

    @Override
    public Service getUserDirRecord(User user, String realPath) {
        if(realPath.charAt(0)=='/') {
            List<String> dirList = GetDirList(realPath);
            System.out.println("DirList : " + dirList);
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
                    Service insRootService = new Service();
                    insRootService.setSysfilerecordid(rootRecord.getId());
                    insRootService.setParentid(-1);
                    insRootService.setUserid(user.getId());
                    insRootService.setStatus(true);
                    insRootService.setDirmask(true);
                    insRootService.setUserfilename("/");
                    insRootService.setUploaddate(new Date());

                    serviceMapper.insert(insRootService);

                    services = serviceMapper.selectByExample(findRootExam);
                    if(!services.isEmpty())
                    {
                        rootService = services.get(0);
                    }else {
                        return null;
                    }

                    return null;
                }

                Service curService = rootService;
                if(realPath.equals("/"))
                {
                    return curService;
                }

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
        if(service.getDirmask())
        {
            ServiceExample example = new ServiceExample();
            ServiceExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(user.getId());
            criteria.andParentidEqualTo(service.getId());

            return serviceMapper.selectByExample(example);
        }

        return null;
    }

    @Override
    public List<Service> getChildren(User user, String curPath) {
        Service userDirRecord = getUserDirRecord(user, curPath);

        if(userDirRecord==null)
            return null;

        return getChildren(user,userDirRecord);
    }

    @Override
    public Service queryByID(int RecordID) {
        return serviceMapper.selectByPrimaryKey(RecordID);
    }

    @Override
    public boolean copyService(Service service, User user,Service workDir) {
        if(service.getDirmask())
        {
            Service InsDirService = new Service();
            InsDirService.setStatus(true);
            InsDirService.setSysfilerecordid(service.getSysfilerecordid());
            InsDirService.setUserfilename(service.getUserfilename());
            InsDirService.setUserid(user.getId());
            InsDirService.setParentid(workDir.getId());
            InsDirService.setDirmask(true);
            InsDirService.setUploaddate(new Date());

            int add = serviceMapper.insert(InsDirService);

            User fromUser = userService.QueryByID(service.getUserid());

            List<Service> children = getChildren(fromUser, service);
            for(Service sub : children)
            {
                copyService(sub,user,workDir);
            }

            return add == 1;
        }else {
            Service insertService = new Service();
            insertService.setUploaddate(new Date());
            insertService.setDirmask(false);
            insertService.setParentid(workDir.getId());
            insertService.setUserfilename(service.getUserfilename());
            insertService.setUserid(user.getId());
            insertService.setStatus(true);
            insertService.setSysfilerecordid(service.getSysfilerecordid());

            return serviceMapper.insert(insertService) == 1;
        }
    }

    @Override
    public List<Service> getWithSysFileTabID(int ID) {
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andSysfilerecordidEqualTo(ID);

        return serviceMapper.selectByExample(example);
    }

    @Override
    public Service queryUniqueService(Service service) {
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();

        criteria.andSysfilerecordidEqualTo(service.getSysfilerecordid());
        criteria.andUseridEqualTo(service.getUserid());
        criteria.andUserfilenameEqualTo(service.getUserfilename());
        criteria.andParentidEqualTo(service.getParentid());

        return serviceMapper.selectByExample(example).get(0);
    }

    @Override
    public boolean updateService(Service service) {
        return serviceMapper.updateByPrimaryKey(service) == 1;
    }

    @Override
    public Service GetUserRoot(User user) {
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getId());
        criteria.andParentidEqualTo(-1);

        return serviceMapper.selectByExample(example).get(0);
    }

    protected List<String> GetDirList(String Path)
    {
        String[] split = Path.split("/");
        ArrayList<String> directories = new ArrayList<>(split.length);
        for(String dir : split)
        {
            if(dir.length()>0)
                directories.add(dir);
        }

        return directories;
    }
}
