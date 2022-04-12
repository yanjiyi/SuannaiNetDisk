package com.suannai.netdisk.model;

public class SysFileTab {
    private Integer id;

    private String filename;

    private String filehash;

    private String location;

    private Long filesize;

    private Boolean inuse;

    public SysFileTab(Integer id, String filename, String filehash, String location, Long filesize, Boolean inuse) {
        this.id = id;
        this.filename = filename;
        this.filehash = filehash;
        this.location = location;
        this.filesize = filesize;
        this.inuse = inuse;
    }

    public SysFileTab() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilehash() {
        return filehash;
    }

    public void setFilehash(String filehash) {
        this.filehash = filehash == null ? null : filehash.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public Boolean getInuse() {
        return inuse;
    }

    public void setInuse(Boolean inuse) {
        this.inuse = inuse;
    }
}