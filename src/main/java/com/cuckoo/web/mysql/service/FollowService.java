package com.cuckoo.web.mysql.service;

import com.cuckoo.web.mysql.dao.UserFollowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tanmq on 2017/3/8.
 */
@Service
public class FollowService {

    @Autowired
    UserFollowDao userFollowDao;


    @Autowired
    FeedService feedService;

    public void follow(Long uid, Long followUid) {
        userFollowDao.follow(uid, followUid);
        feedService.distributeFeeds(uid, followUid);
    }

    public void unFollow(Long uid, Long followUid) {
        userFollowDao.unFollow(uid, followUid);
        feedService.hideUserFeeds(uid, followUid);
    }



}
