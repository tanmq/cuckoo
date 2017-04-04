package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.mapper.UserMapper;
import com.cuckoo.web.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tanmq on 2017/2/26.
 */
@Component
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.getALl();
    }

    public User getUserByPhone(String phone) {
        if (StringUtil.NullOrEmpty(phone)) {
            return null;
        }

        return userMapper.getUserByPhone(phone);
    }


    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }


    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public Integer countUsers() {
        return userMapper.countUsers();
    }

    public void updateUserName(long id, String name) {
        userMapper.updateUserName(id, name);
    }

    public void updateUserAvatar(long id, String avatarUrl, String avatarUrlOrigin) {
        userMapper.updateUserAvatar(id, avatarUrl, avatarUrlOrigin);
    }

    public void updateUserGender(long id, int gender) {
        userMapper.updateUserGender(id, gender);
    }

    public void updateUserArea(long id, String area) {
        userMapper.updateUserArea(id, area);
    }

    public void updateUserSignature(long id, String signature) {
        userMapper.updateUserSignature(id, signature);
    }

    public void updateUserCoverImg(long id, String coverUrl) {
        userMapper.updateUserCoverImg(id, coverUrl);
    }

}
