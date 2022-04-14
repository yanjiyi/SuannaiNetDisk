package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.TaskMapper;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public boolean createTask(Task task) {
        int insert = taskMapper.insert(task);
        return insert > 0;
    }

    @Override
    public boolean lockTask(Task task) {
        task.setIdle(false);
        int i = taskMapper.updateByPrimaryKey(task);
        return i == 1;
    }

    @Override
    public boolean deleteTask(Task task) {
        int i = taskMapper.deleteByPrimaryKey(task.getId());
        return i == 1;
    }

    @Override
    public Task queryByID(int ID) {
        return taskMapper.selectByPrimaryKey(ID);
    }

    @Override
    public boolean updateTask(Task task) {
        return taskMapper.updateByPrimaryKey(task) == 1;
    }
}
