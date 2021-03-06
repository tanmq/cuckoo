package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.common.Constant;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.ddl.UserFollow;
import com.cuckoo.web.mysql.mapper.UserFollowMapper;
import com.cuckoo.web.mysql.mapper.UserMapper;
import com.cuckoo.web.utils.IntegerUtil;
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


    @Autowired
    UserMapper userMapper;

    public void follow(Long uid, Long followUid) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(followUid)) {
            return;
        }

        userFollowMapper.follow(uid, followUid);
        userMapper.incrementFollowCount(uid);
        userMapper.incrementFollowedCount(followUid);
    }

    public void unFollow(Long uid, Long followUid) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(followUid)) {
            return;
        }
        userFollowMapper.unFollow(uid, followUid);
        userMapper.decrementFollowCount(uid);
        userMapper.decrementFollowedCount(followUid);
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

    public List<User> getFollowers(Long uid, Integer page, Integer size) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }

        if (IntegerUtil.NullORZero(page)) {
            page = Constant.DEFAULT_PAGE;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_PAGE_SIZE;
        }

        int offset = (page - 1) * size;
        int count  = size;

        return userFollowMapper.getFollowers(uid, offset, count);
    }

    public List<User> getFollowees(Long uid, Integer page, Integer size) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }

        if (IntegerUtil.NullORZero(page)) {
            page = Constant.DEFAULT_PAGE;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_PAGE_SIZE;
        }

        int offset = (page - 1) * size;
        int count  = size;

        return userFollowMapper.getFollowees(uid, offset, count);
    }


    public boolean hasFollow(long uid, long targetUid) {
        return userFollowMapper.hasFollow(uid, targetUid) > 0;
    }



}
