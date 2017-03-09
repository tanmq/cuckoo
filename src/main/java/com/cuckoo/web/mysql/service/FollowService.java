package com.cuckoo.web.mysql.service;

import com.cuckoo.web.mysql.dao.UserFollowDao;
import com.cuckoo.web.mysql.ddl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        //you should open this while want to see old feeds from followUid.
        //feedService.distributeFeeds(uid, followUid);
    }

    public void unFollow(Long uid, Long followUid) {
        userFollowDao.unFollow(uid, followUid);
        //you should open this while want not to see old feeds from followUid.
        //feedService.hideUserFeeds(uid, followUid);
    }

    public List<User> getFollowUsers(Long uid) {
        return userFollowDao.getFollowUsers(uid);
    }



}
