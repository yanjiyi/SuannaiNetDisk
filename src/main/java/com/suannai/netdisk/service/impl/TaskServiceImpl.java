package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.TaskMapper;
import com.suannai.netdisk.model.Task;
import com.suannai.netdisk.model.TaskExample;
import com.suannai.netdisk.service.TaskService;
import com.suannai.netdisk.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskTypeService taskTypeService;

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

    @Override
    public List<Task> queryUserDownloadTask(int UserID) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(UserID);
        criteria.andTasktypeEqualTo(taskTypeService.GetTaskID("Download"));

        return taskMapper.selectByExample(example);
    }

    @Override
    public Task queryUserDownloadTaskByID(int UserID,int ID) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTasktypeEqualTo(taskTypeService.GetTaskID("Download"));
        criteria.andIdEqualTo(ID);
        criteria.andUseridEqualTo(UserID);

        return taskMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Task> queryUserTask(int UserID) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(UserID);

        return taskMapper.selectByExample(example);
    }

    @Override
    public List<Task> queryToUserTask(int UserID) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTargetidEqualTo(UserID);

        return taskMapper.selectByExample(example);
    }
}
