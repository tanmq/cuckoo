package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.ddl.UserFollow;
import com.cuckoo.web.mysql.mapper.UserFollowMapper;
import com.cuckoo.web.utils.LongUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Component
public class UserFollowDao {

    @Autowired
    UserFollowMapper userFollowMapper;

    public void follow(Long uid, Long followUid) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(followUid)) {
            return;
        }

        userFollowMapper.follow(uid, followUid);
    }

    public void unFollow(Long uid, Long followUid) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(followUid)) {
            return;
        }
        userFollowMapper.unFollow(uid, followUid);
    }

    public List<UserFollow> getUserFollowers(Long uid) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }
        return userFollowMapper.getUserFollowers(uid);
    }

    public List<UserFollow> getFollowingUsers(Long uid) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }
        return userFollowMapper.getUserFollowers(uid);
    }

    public List<User> getFollowUsers(Long uid) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }

        return userFollowMapper.getFollowUsers(uid);
    }


}
