package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.FeedService;
import com.cuckoo.web.utils.IDUtil;
import com.cuckoo.web.utils.ReqUtil;
import com.cuckoo.web.utils.RespUtil;
import com.cuckoo.web.utils.TUser;
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

    @Autowired
    FeedService feedService;

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

        User user = TUser.getUser();
        List<Feed> feeds = feedService.getUserFeeds(user.getId(), page, size);

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
            JSONObject item = new JSONObject();
            item.put("id", f.getId());
            item.put("uid", f.getUid());
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
