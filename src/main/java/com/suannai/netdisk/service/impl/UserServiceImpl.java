package com.suannai.netdisk.service.impl;

import com.suannai.netdisk.mapper.UserMapper;
import com.suannai.netdisk.model.User;
import com.suannai.netdisk.model.UserExample;
import com.suannai.netdisk.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createUser(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty())
        {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            user.setStatus(true);

            int insert = userMapper.insert(user);
            return insert > 0;
        }
        return false;
    }

    @Override
    public boolean login(User user, String strIP, HttpSession session) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty())
        {
            User vailedUser = users.get(0);
            if(vailedUser.getPassword().equals(user.getPassword()) && vailedUser.getStatus())
            {
                vailedUser.setLastlogintime(new Date());
                vailedUser.setLastloginip(strIP);

                userMapper.updateByPrimaryKey(vailedUser);

                session.setAttribute("user",vailedUser);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean blockUser(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty())
        {
            User operUser = users.get(0);
            operUser.setStatus(false);

            return userMapper.updateByPrimaryKey(operUser) == 1;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return userMapper.deleteByPrimaryKey(user.getId()) == 1;
    }

    @Override
    public User QueryByID(int ID) {
        return userMapper.selectByPrimaryKey(ID);
    }

    @Override
    public User QueryByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty())
        {
            return null;
        }

        return users.get(0);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKey(user) == 1;
    }


}
