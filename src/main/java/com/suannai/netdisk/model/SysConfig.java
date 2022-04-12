package com.suannai.netdisk.model;

public class SysConfig {
    private Integer id;

    private String name;

    private String value;

    public SysConfig(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public SysConfig() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}