package com.cuckoo.web.mysql.service;

import com.cuckoo.web.mysql.dao.UserDao;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.utils.LongUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tanmq on 2017/3/11.
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;


    public User getUser(Long uid) {
        if (LongUtil.NullORZero(uid)) {
            return null;
        }

        return userDao.getUserById(uid);
    }
}
