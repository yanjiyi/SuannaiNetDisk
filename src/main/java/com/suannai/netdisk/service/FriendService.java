package com.suannai.netdisk.service;

import com.suannai.netdisk.model.Friend;
import org.springframework.stereotype.Component;

import java.util.List;

public interface FriendService {
    List<Friend> queryFriends(int id);
    Friend findByID(int id);
    boolean deleteFriendsByID(int ID);
    boolean addFriend(Friend friend);
    boolean DisconnectFriend(int FrdId,int Ownner);
    List<Friend> listMyFriends(int UserID);
}
