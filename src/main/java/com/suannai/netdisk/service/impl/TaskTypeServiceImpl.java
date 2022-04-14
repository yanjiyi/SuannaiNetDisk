package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.TaskTypeMapper;
import com.suannai.netdisk.model.TaskType;
import com.suannai.netdisk.model.TaskTypeExample;
import com.suannai.netdisk.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    TaskTypeMapper taskTypeMapper;

    @Override
    public List<TaskType> queryAll() {
        return taskTypeMapper.selectByExample(new TaskTypeExample());
    }

    @Override
    public int GetTaskID(String name) {
        TaskTypeExample example = new TaskTypeExample();
        TaskTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTasknameNotEqualTo(name);
        return taskTypeMapper.selectByExample(example).get(0).getId();
    }
}
