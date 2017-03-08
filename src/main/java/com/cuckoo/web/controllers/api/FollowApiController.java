package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.service.FollowService;
import com.cuckoo.web.utils.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanmq on 2017/3/8.
 */
@RestController
@RequestMapping("/friend")
public class FollowApiController {

    private static Logger logger = LoggerFactory.getLogger(FollowApiController.class);

    @Autowired
    FollowService followService;


    @RequestMapping(value = "follow", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject follow(@RequestBody JSONObject req) throws Exception{



        return RespUtil.OKResponse();
    }

    @RequestMapping(value = "unFollow", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject unFollow(@RequestBody JSONObject req) throws Exception{



        return RespUtil.OKResponse();
    }


    @RequestMapping(value = "followers", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject followers() throws Exception {



        return null;
    }

    @RequestMapping(value = "recommend", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject recommendFriends() throws Exception {



        return null;
    }

}
