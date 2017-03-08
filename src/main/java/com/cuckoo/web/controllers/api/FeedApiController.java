package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.service.FeedService;
import com.cuckoo.web.utils.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



        return RespUtil.OKResponse();
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject feedList(@RequestBody JSONObject req) throws Exception{


        return null;
    }


    @RequestMapping(value = "timeline", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject timeline(@RequestBody JSONObject req) throws Exception {


        return null;
    }


    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject detail(@RequestBody JSONObject req) throws Exception {


        return null;
    }




}
