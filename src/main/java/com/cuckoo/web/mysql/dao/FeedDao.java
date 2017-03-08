package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.common.Constant;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.mapper.FeedMapper;
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
public class FeedDao {

    @Autowired
    FeedMapper feedMapper;

    public void insertFeed(Feed feed) {
        if (feed == null) {
            return;
        }

        feedMapper.insertFeed(feed);
    }


    public void updateFeed(Feed feed) {
        if (feed == null) {
            return;
        }

        feedMapper.updateFeed(feed);
    }

    public void deleteFeed(Long id) {
        if (LongUtil.NullORZero(id)) {
            return;
        }

        feedMapper.deleteFeed(id);
    }

    public List<Feed> getUserFeeds(Long uid, Integer offset, Integer size) {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }

        if (IntegerUtil.NullORZero(offset)) {
            offset = Constant.DEFAULT_OFFSET;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_SIZE;
        }

        return feedMapper.getUserFeeds(uid, offset, size);
    }

    public Feed getFeed(Long id) {
        if (LongUtil.NullORZero(id)) {
            return null;
        }

        return feedMapper.getFeed(id);
    }
}
