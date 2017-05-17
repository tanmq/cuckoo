package com.cuckoo.web.mysql.service;

import com.cuckoo.web.mysql.dao.UserFollowDao;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.utils.LongUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Service
public class FollowService {

    private static final Logger logger = LoggerFactory.getLogger(FollowService.class);

    @Autowired
    UserFollowDao userFollowDao;


    @Autowired
    FeedService feedService;

    public void follow(Long uid, Long followUid) {
        userFollowDao.follow(uid, followUid);

        //you should open this while want to see old feeds from followUid.
        feedService.distributeFeeds(uid, followUid);
    }

    public void unFollow(Long uid, Long followUid) {
        userFollowDao.unFollow(uid, followUid);
        //you should open this while want not to see old feeds from followUid.
        feedService.hideUserFeeds(uid, followUid);
    }


    public boolean hasFollow(Long uid, Long targetUid) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(targetUid)) {
            logger.warn("Invalid userID. uid={}, target={}", uid, targetUid);
            return false;
        }
        return userFollowDao.hasFollow(uid, targetUid);
    }

    public List<User> getFollowers(Long uid, Integer page, Integer size) {
        return userFollowDao.getFollowers(uid, page, size);
    }


    public List<User> getFollowees(Long uid, Integer page, Integer size) {
        return userFollowDao.getFollowees(uid, page, size);
    }



}
