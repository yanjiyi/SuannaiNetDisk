package com.suannai.netdisk.dao;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class tellStatus {
    String gid;
    String status;
    BigInteger totalLength;
    BigInteger completedLength;
    BigInteger uploadLength;
    Byte bitfield;
    BigInteger downloadSpeed;
    BigInteger uploadSpeed;
    String infoHash;
    int numSeeders;
    boolean seeder;
    BigInteger pieceLength;
    int numPieces;
    int connections;
    String errorCode;
    String errorMessage;
    String belongsTo;
    String dir;
    List<Files> files;
    tellStatusBittorrent bittorrent;
    BigInteger verifiedLength;
    boolean verifyIntegrityPending;
}
