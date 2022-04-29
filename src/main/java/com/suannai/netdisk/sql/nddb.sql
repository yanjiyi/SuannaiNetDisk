SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop database if exists `nddb`;
create database `nddb`;

use nddb;
drop table if exists `sysconfig`;
create table `sysconfig`(
  `ID` int primary key auto_increment,
  `Name` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL UNIQUE,
  `Value` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `sysfiletab`;
create table `sysfiletab`(
  `ID` int primary key auto_increment,
  `FileName` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FileHash` varchar(32) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL UNIQUE,
  `Location` varchar(9999) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FileSize` bigint NOT NULL,
  `InUse` bit(1) NOT NULL,
  `RootMask` bit(1) NOT NULL default 0x0
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `friends`;
create table `friends`(
  `Id` int primary key auto_increment,
  `FrdID` int NOT NULL,
  `Ownner` int NOT NULL,
  `Who` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Date` datetime NOT NULL default CURRENT_TIMESTAMP
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `tasktype`;
create table `tasktype`(
  `ID` int primary key auto_increment,
  `TaskName` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL UNIQUE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `task`;
create table `task`(
  `ID` int primary key auto_increment,
  `TaskType` int NOT NULL,
  `Date` datetime NOT NULL default CURRENT_TIMESTAMP,
  `UserID` int NOT NULL,
  `TargetID` int NOT NULL,
  `TaskStatus` bit(1) NOT NULL default 0,
  `Additional` int default -1,
  `Idle` bit(1) NOT NULL default 1,
  `GID` varchar(255),
  `Aria2ID` varchar(255)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `superusers`;
create table `superusers`(
  `ID` int primary key auto_increment,
  `UserName` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(32) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastLoginTime` datetime,
  `LastLoginIP` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Status` bit(1) NOT NULL default 1
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `users`;
create table `users`(
  `ID` int primary key auto_increment,
  `UserName` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL UNIQUE,
  `Password` varchar(32) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastLoginTime` datetime,
  `LastLoginIP` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Status` bit(1) NOT NULL default 1,
  `ImgServiceID` int default -1,
  `NickName` varchar(50) default 'test'
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `userlog`;
create table `userlog`(
  `ID` int primary key auto_increment,
  `UserID` int NOT NULL,
  `SuperUser` bit(1) NOT NULL default 0,
  `OperationDescription` varchar(255) NOT NULL,
  `Date` datetime NOT NULL default CURRENT_TIMESTAMP  
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists `service`;
create table `service`(
  `ID` int primary key auto_increment,
  `UserID` int NOT NULL,
  `UserFileName` varchar(255) character set utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SysFileRecordID` int NOT NULL,
  `Status` bit(1) NOT NULL default 1,
  `UploadDate` datetime NOT NULL default CURRENT_TIMESTAMP,
  `ParentID` int,
  `DirMask` bit(1) NOT NULL default 0
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

alter table `tasktype` auto_increment = 1;

insert into `tasktype`(TaskName) value("Friend");
insert into `tasktype`(TaskName) value("Share");
insert into `tasktype`(TaskName) value("Download");

insert into `sysconfig`(Name,Value) value("AllowLogin","YES");
insert into `sysconfig`(Name,Value) value("AllowRegister","YES");
insert into `sysconfig`(Name,Value) value("AllowCD","YES");
insert into `sysconfig`(Name,Value) value("AllowListCur","YES");
insert into `sysconfig`(Name,Value) value("AllowShare","YES");
insert into `sysconfig`(Name,Value) value("AllowAddDownloadTask","YES");
insert into `sysconfig`(Name,Value) value("AllowAddFriend","YES");
insert into `sysconfig`(Name,Value) value("AllowAcceptFriend","YES");
insert into `sysconfig`(Name,Value) value("AllowRefuseFriend","YES");
insert into `sysconfig`(Name,Value) value("AllowPwd","YES");
insert into `sysconfig`(Name,Value) value("AllowGetCurService","YES");
insert into `sysconfig`(Name,Value) value("AllowUpload","YES");
insert into `sysconfig`(Name,Value) value("AllowDownload","YES");
insert into `sysconfig`(Name,Value) value("AllowAcceptShare","YES");
insert into `sysconfig`(Name,Value) value("AllowRefuseShare","YES");
insert into `sysconfig`(Name,Value) value("AllowDeleteTask","YES");
insert into `sysconfig`(Name,Value) value("AllowLogout","YES");
insert into `sysconfig`(Name,Value) value("ListMyFriends","YES");
insert into `sysconfig`(Name,Value) value("AllowDeleteDownloadTask","YES");
insert into `sysconfig`(Name,Value) value("AllowGetAllDownloadTasks","YES");
insert into `sysconfig`(Name,Value) value("AllowGetDownloadTask","YES");
insert into `sysconfig`(Name,Value) value("AllowListMyTask","YES");
insert into `sysconfig`(Name,Value) value("AllowListToMyTask","YES");
insert into `sysconfig`(Name,Value) value("AllowGetTaskType","YES");
insert into `sysconfig`(Name,Value) value("AllowQuerySysFileTab","YES");
insert into `sysconfig`(Name,Value) value("AllowGetUser","YES");
insert into `sysconfig`(Name,Value) value("AllowQueryService","YES");
insert into `sysconfig`(Name,Value) value("AllowSetUserImg","YES");
insert into `sysconfig`(Name,Value) value("AllowSetUser","YES");
insert into `sysconfig`(Name,Value) value("AllowDeleteService","YES");
insert into `sysconfig`(Name,Value) value("AllowMoveTo","YES");
insert into `sysconfig`(Name,Value) value("SysFileTabAllowErase","YES");
insert into `sysconfig`(Name,Value) value("AllowDeleteFriend","YES");
insert into `sysconfig`(Name,Value) value("AlloListAllTaskType","YES");
insert into `sysconfig`(Name,Value) value("AllowGetUserName","YES");
insert into `sysconfig`(Name,Value) value("AllowGetTaskDeleteUrl","YES");

insert into `sysconfig`(Name,Value) value("uploadPath","/data/netdisk/upload");