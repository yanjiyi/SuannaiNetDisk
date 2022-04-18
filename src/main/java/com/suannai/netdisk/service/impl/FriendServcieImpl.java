package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.FriendMapper;
import com.suannai.netdisk.model.Friend;
import com.suannai.netdisk.model.FriendExample;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendServcieImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<Friend> queryFriends(int id) {
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.createCriteria();
        criteria.andOwnnerEqualTo(id);
        return friendMapper.selectByExample(example);
    }

    @Override
    public Friend findByID(int id) {
        return friendMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteFriendsByID(int ID) {
        return friendMapper.deleteByPrimaryKey(ID) == 1;
    }

    @Override
    public boolean addFriend(Friend friend) {
        return friendMapper.insert(friend) == 1;
    }

    @Override
    public boolean DisconnectFriend(int FrdId, int Ownner) {
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.createCriteria();
        criteria.andOwnnerEqualTo(Ownner);
        criteria.andFrdidEqualTo(FrdId);

        List<Friend> friends = friendMapper.selectByExample(example);

        FriendExample example1 = new FriendExample();
        FriendExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOwnnerEqualTo(FrdId);
        criteria1.andFrdidEqualTo(Ownner);

        List<Friend> friends1 = friendMapper.selectByExample(example1);

        boolean first = false;
        boolean second = false;

        for(Friend frd : friends)
        {
            if(deleteFriendsByID(frd.getId()))
            {
                first = true;
            }
        }

        for(Friend frd : friends1)
        {
            if(deleteFriendsByID(frd.getId()))
            {
                second = true;
            }
        }

        if(first && second)
            return true;

        return false;
    }

    @Override
    public List<Friend> listMyFriends(int UserID) {
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.createCriteria();
        criteria.andOwnnerEqualTo(UserID);

        return friendMapper.selectByExample(example);
    }
}
