package com.suannai.netdisk.dao;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class Files {
    int index;
    String path;
    BigInteger length;
    BigInteger completedLength;
    boolean selected;
    List<Uris> uris;
}
