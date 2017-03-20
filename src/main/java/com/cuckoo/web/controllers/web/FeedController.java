package com.cuckoo.web.controllers.web;

import com.cuckoo.web.controllers.api.FeedApiController;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.service.FeedService;
import com.cuckoo.web.utils.LongUtil;
import com.cuckoo.web.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tanmq on 2017/3/20.
 */
@Controller
@RequestMapping("/feed")
public class FeedController {

    private static Logger logger = LoggerFactory.getLogger(FeedApiController.class);

    @Autowired
    FeedService feedService;


    @RequestMapping(value = "/{fid}/{code}", method = RequestMethod.GET)
    public String feedShare(@PathVariable Long fid, @PathVariable String code) {
        if (LongUtil.NullORZero(fid) || StringUtil.NullOrEmpty(code)) {
            //redirect
        }

        Feed feed = feedService.getFeedDetail(fid);
        if (feed == null) {
            //redirect
        }

        String shareCode = feed.getShareCode();
        if (StringUtil.NullOrEmpty(shareCode) || !shareCode.equals(code)) {
            //redirect
        }

        return "";
    }




}
