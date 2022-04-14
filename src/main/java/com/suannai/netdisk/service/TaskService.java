package com.suannai.netdisk.service;

import com.suannai.netdisk.model.Task;

public interface TaskService {
    boolean createTask(Task task);
    boolean lockTask(Task task);
    boolean deleteTask(Task task);
    Task queryByID(int ID);
    boolean updateTask(Task task);
}
