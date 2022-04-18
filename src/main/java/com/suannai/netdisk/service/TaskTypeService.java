package com.suannai.netdisk.service;

import com.suannai.netdisk.model.TaskType;

import java.util.List;

public interface TaskTypeService {
    List<TaskType> queryAll();
    int GetTaskID(String name);

    String GetTaskTypeName(int ID);
}
