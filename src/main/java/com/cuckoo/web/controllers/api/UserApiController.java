package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.UserService;
import com.cuckoo.web.utils.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanmq on 2017/3/11.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    private static Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/vCard/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject vCard(@PathVariable long uid) throws Exception {

        User user = userService.getUser(uid);
        if (user == null) {
            return RespUtil.ERRORResponse(410, "user not exist.");
        }

        JSONObject ret = new JSONObject();
        ret.put("name", user.getName());
        ret.put("avatar_url", user.getAvatarUrl());
        ret.put("id", user.getId());
        ret.put("phone", user.getPhone());

        return ret;
    }





}
