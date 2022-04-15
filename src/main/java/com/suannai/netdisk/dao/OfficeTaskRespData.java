package com.suannai.netdisk.dao;

import lombok.Data;

@Data
public class OfficeTaskRespData {
    int statusCode;
    String message;
    String gid;
    String aria2id;
    String taskid;
}
