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

    public User getUserByEmail(String email) {
        if (StringUtil.NullOrEmpty(email)) {
            return null;
        }

        return userMapper.getUserByEmail(email);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
