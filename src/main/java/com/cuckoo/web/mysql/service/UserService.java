package com.cuckoo.web.mysql.service;

import com.cuckoo.web.controllers.enumutation.VCardInfoType;
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

    public void updateUserInfo(VCardInfoType type, User user) {
        if (LongUtil.NullORZero(user.getId())) {
            logger.error("Invalid user id.");
            return;
        }

        switch (type) {
            case COVER:
                userDao.updateUserCoverImg(user.getId(), user.getCoverUrl());
                break;
            case SIGNATURE:
                userDao.updateUserSignature(user.getId(), user.getSignature());
                break;
            case AREA:
                userDao.updateUserArea(user.getId(), user.getArea());
                break;
            case AVATAR:
                userDao.updateUserAvatar(user.getId(), user.getAvatarUrl(), user.getAvatarUrlOrigin());
                break;
            case GENDER:
                userDao.updateUserGender(user.getId(), user.getGender());
                break;
            case NAME:
                userDao.updateUserName(user.getId(), user.getName());
                break;
            default:
                logger.warn("Unknown VCardInfo type. type value is {}", type.getType());
                break;
        }
    }
}
