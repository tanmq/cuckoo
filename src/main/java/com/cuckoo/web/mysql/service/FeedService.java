package com.cuckoo.web.mysql.service;

import com.cuckoo.web.common.Constant;
import com.cuckoo.web.mysql.dao.FeedDao;
import com.cuckoo.web.mysql.dao.FeedTimelineDao;
import com.cuckoo.web.mysql.dao.UserFollowDao;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.ddl.UserFollow;
import com.cuckoo.web.utils.IntegerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Service
public class FeedService {

    @Autowired
    FeedDao feedDao;

    @Autowired
    UserFollowDao userFollowDao;

    @Autowired
    FeedTimelineDao feedTimelineDao;


    public void distributeFeeds(Long uid, Long followUid) {
        //TODO 这个可以异步去做
        int offset = 0, size = 100;
        List<Feed> feeds = feedDao.getUserFeeds(followUid, offset, size);
        while (!feeds.isEmpty()) {
            for (Feed feed : feeds) {
                feedTimelineDao.insertTimelineItem(uid, feed.getId(), feed.getUid(), feed.getCts());
            }

            offset = offset + size;
            feeds = feedDao.getUserFeeds(followUid, offset, size);
        }
    }

    public void hideUserFeeds(Long uid, Long followUid) {
        //TODO 可以异步
        feedTimelineDao.hideUserFeeds(uid, followUid);
    }

    public List<Feed> getTimelineFeeds(Long uid, Integer page, Integer size) {
        if (IntegerUtil.NullORZero(page)) {
            page = Constant.DEFAULT_PAGE;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_PAGE_SIZE;
        }

        return feedTimelineDao.getTimeLine(uid, (page - 1)*size, size);
    }


    public Feed getFeedDetail(Long id) {
        return feedDao.getFeed(id);
    }

    public List<Feed> getUserFeeds(Long uid, Integer page, Integer size) {
        if (IntegerUtil.NullORZero(page)) {
            page = Constant.DEFAULT_PAGE;
        }

        if (IntegerUtil.NullORZero(size)) {
            size = Constant.DEFAULT_PAGE_SIZE;
        }

        return feedDao.getUserFeeds(uid, (page - 1)*size, size);
    }


    public void newFeed(Feed feed) {
        feedDao.insertFeed(feed);

        //insert into own timeline
        feedTimelineDao.insertTimelineItem(feed.getUid(), feed.getId(), feed.getUid(), feed.getCts());

        //TODO 异步去做
        //insert into followers timeline
        List<UserFollow> follows = userFollowDao.getUserFollowers(feed.getUid());
        for (UserFollow follow : follows) {
            feedTimelineDao.insertTimelineItem(follow.getUid(), feed.getId(), feed.getUid(), feed.getCts());
        }
    }

    public void updateFeed(Feed feed) {
        feedDao.updateFeed(feed);
    }

}
