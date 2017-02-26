package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.mapper.UserMapper;
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
}
