package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.FeedService;
import com.cuckoo.web.mysql.service.UserService;
import com.cuckoo.web.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@RestController
@RequestMapping("/api/v1/feed")
public class FeedApiController {

    private static Logger logger = LoggerFactory.getLogger(FeedApiController.class);

    private static final String shareUrlPrefix = Config.get("share.url.prefix");

    @Autowired
    FeedService feedService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject newFeed(@RequestBody JSONObject req) throws Exception {
        ReqUtil.NotNullParams(req, "title", "desc", "coverImg", "content");

        User user = TUser.getUser();

        String title        = req.getString("title");
        String desc         = req.getString("desc");
        String coverImg     = req.getString("coverImg");
        JSONArray content   = req.getJSONArray("content");

        Feed feed = new Feed();
        feed.setUid(user.getId());
        feed.setTitle(title);
        feed.setDesc(desc);
        feed.setCoverImg(coverImg);
        feed.setContent(content.toJSONString());
        feed.setShareCode(IDUtil.newShareCode());

        feedService.newFeed(feed);

        return RespUtil.OKResponse();
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject feedList(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "page", "size");

        Integer page = req.getInteger("page");
        Integer size = req.getInteger("size");

        Long uid;
        if (req.containsKey("uid")) {
            uid = req.getLong("uid");
            if (LongUtil.NullORZero(uid)) {
                return RespUtil.ERRORResponse(405, "invalid uid");
            }
        } else {
            uid = TUser.getUser().getId();
        }

        List<Feed> feeds = feedService.getUserFeeds(uid, page, size);

        return RespUtil.OKResponse(buildFeedList(page, feeds));
    }


    @RequestMapping(value = "timeline", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject timeline(@RequestBody JSONObject req) throws Exception {
        ReqUtil.NotNullParams(req, "page", "size");

        Integer page = req.getInteger("page");
        Integer size = req.getInteger("size");

        User user = TUser.getUser();
        List<Feed> feeds = feedService.getTimelineFeeds(user.getId(), page, size);

        return RespUtil.OKResponse(buildFeedList(page, feeds));
    }


    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject detail(@RequestBody JSONObject req) throws Exception {
        ReqUtil.NotNullParams(req, "fid");

        Long fid = req.getLong("fid");

        Feed feed = feedService.getFeedDetail(fid);
        if (feed == null) {
            return RespUtil.ERRORResponse(409, "feed not exist.");
        }

        JSONObject data = new JSONObject();
        data.put("id", feed.getId());
        data.put("title", feed.getTitle());
        data.put("coverImg", feed.getCoverImg());
        data.put("desc", feed.getDesc());
        data.put("cts", feed.getCts().getTime());
        data.put("content", JSON.parseArray(feed.getContent()));

        return RespUtil.OKResponse(data);
    }

    @RequestMapping(value = "/share/{fid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject share(@PathVariable Long fid) {
        if (LongUtil.NullORZero(fid)) {
            return RespUtil.ERRORResponse(400, "error fid");
        }

        Feed feed = feedService.getFeedDetail(fid);
        if (feed == null) {
            return RespUtil.ERRORResponse(400, "feed not exist.");
        }

        if (StringUtil.NullOrEmpty(feed.getShareCode())) {
            feed.setShareCode(IDUtil.newShareCode());
            feedService.updateFeed(feed);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(shareUrlPrefix);
        sb.append("/").append(feed.getId());
        sb.append("/").append(feed.getShareCode());

        JSONObject data = new JSONObject();
        data.put("share_url", sb.toString());

        return RespUtil.OKResponse(data);
    }


    /**
     * 封装列表数据返回
     * @param page
     * @param feeds
     * @return
     */
    private JSONObject buildFeedList(Integer page, List<Feed> feeds) {
        JSONObject data = new JSONObject();
        data.put("page", page);
        data.put("count", feeds.size());
        JSONArray array = new JSONArray();
        for (Feed f : feeds) {

            //TODO this need to remove for the performance in the future.
            User user = userService.getUser(f.getUid());
            if (user == null) {
                continue;
            }

            JSONObject item = new JSONObject();
            item.put("id", f.getId());
            item.put("uid", f.getUid());
            item.put("user_name", user.getName());
            item.put("user_avatar_url", user.getAvatarUrl());

            item.put("title", f.getTitle());
            item.put("coverImg", f.getCoverImg());
            item.put("desc", f.getDesc());
            item.put("cts", f.getCts().getTime());
            array.add(item);
        }

        data.put("list", array);

        return data;
    }





}
