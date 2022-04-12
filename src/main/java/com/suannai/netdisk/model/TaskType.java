package com.suannai.netdisk.model;

public class TaskType {
    private Integer id;

    private String taskname;

    public TaskType(Integer id, String taskname) {
        this.id = id;
        this.taskname = taskname;
    }

    public TaskType() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }
}