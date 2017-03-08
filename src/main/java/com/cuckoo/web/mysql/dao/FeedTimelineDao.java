package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.common.Constant;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.mapper.FeedTimelineMapper;
import com.cuckoo.web.utils.IntegerUtil;
import com.cuckoo.web.utils.LongUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Component
public class FeedTimelineDao {

    @Autowired
    FeedTimelineMapper feedTimelineMapper;

    public List<Feed> getTimeLine(Long uid, Integer offset, Integer size)  {
        if (LongUtil.NullORZero(uid)) {
            return new ArrayList<>();
        }

        if (IntegerUtil.NullORZero(offset)) {
            offset = Constant.DEFAULT_OFFSET;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_SIZE;
        }

        return feedTimelineMapper.getTimeLine(uid, offset, size);
    }

    public void insertTimelineItem(Long uid, Long fid, Long authorId, Date cts) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(fid) || LongUtil.NullORZero(authorId)) {
            return;
        }

        feedTimelineMapper.insertTimelineItem(uid, fid, authorId, cts);
    }

    public void hideUserFeeds(long uid, long authorId) {
        if (LongUtil.NullORZero(uid) || LongUtil.NullORZero(authorId)) {
            return;
        }

        feedTimelineMapper.hideUserFeeds(uid, authorId);
    }



}
