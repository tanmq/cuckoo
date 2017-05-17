package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.AccountService;
import com.cuckoo.web.mysql.service.FollowService;
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
@RequestMapping("/api/v1/friend")
public class FollowApiController {

    private static Logger logger = LoggerFactory.getLogger(FollowApiController.class);

    @Autowired
    FollowService followService;

    @Autowired
    AccountService accountService;


    @RequestMapping(value = "follow", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject follow(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "follow_uid");

        User user       = TUser.getUser();
        Long followUid  = req.getLong("follow_uid");

        followService.follow(user.getId(), followUid);

        return RespUtil.OKResponse();
    }

    @RequestMapping(value = "unFollow", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject unFollow(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "follow_uid");

        User user       = TUser.getUser();
        Long followUid  = req.getLong("follow_uid");
        followService.unFollow(user.getId(), followUid);

        return RespUtil.OKResponse();
    }


    @RequestMapping(value = "followers", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject followers(@RequestBody JSONObject req) throws Exception {

        ReqUtil.NotNullParams(req, "page", "size");

        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        User user = TUser.getUser();
        Long uid  = user.getId();

        Integer page = req.getInteger("page");
        Integer size = req.getInteger("size");

        if (req.containsKey("uid")) {
            uid = req.getLong("uid");
        }

        List<User> followers = followService.getFollowers(uid, page, size);
        for (User f : followers) {
            JSONObject m = new JSONObject();
            m.put("uid", f.getId());
            m.put("name", f.getName());
            m.put("gender", f.getGender());
            m.put("avatar_url", f.getAvatarUrl());
            m.put("area", f.getArea() == null ? "" : f.getArea());
            m.put("signature", f.getSignature() == null ? "" : f.getSignature());
            if (followService.hasFollow(f.getId(), uid)) {
                m.put("followed", 1);
            } else {
                m.put("followed", 0);
            }

            array.add(m);
        }

        data.put("page", page);
        data.put("count", followers.size());
        data.put("list", array);

        return RespUtil.OKResponse(data);
    }


    @RequestMapping(value = "followees",  method = RequestMethod.POST)
    @ResponseBody
    public JSONObject followees(@RequestBody JSONObject req) throws Exception {

        ReqUtil.NotNullParams(req, "page", "size");

        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        User user = TUser.getUser();
        Long uid  = user.getId();

        Integer page = req.getInteger("page");
        Integer size = req.getInteger("size");

        if (req.containsKey("uid")) {
            uid = req.getLong("uid");
        }

        List<User> followees = followService.getFollowees(uid, page, size);
        for (User f : followees) {
            JSONObject m = new JSONObject();
            m.put("uid", f.getId());
            m.put("name", f.getName());
            m.put("gender", f.getGender());
            m.put("avatar_url", f.getAvatarUrl());
            m.put("area", f.getArea() == null ? "" : f.getArea());
            m.put("signature", f.getSignature() == null ? "" : f.getSignature());
            if (followService.hasFollow(uid, f.getId())) {
                m.put("follow", 1);
            } else {
                m.put("follow", 0);
            }


            array.add(m);
        }

        data.put("page", page);
        data.put("count", followees.size());
        data.put("list", array);

        return RespUtil.OKResponse(data);
    }

    @RequestMapping(value = "recommend", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject recommendFriends() throws Exception {



        return null;
    }

}
