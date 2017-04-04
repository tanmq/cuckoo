package com.cuckoo.web.controllers.api;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.controllers.enumutation.VCardInfoType;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.UserService;
import com.cuckoo.web.utils.*;
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
        ret.put("avatar_url_origin", user.getAvatarUrlOrigin());
        ret.put("id", user.getId());
        ret.put("area", user.getArea());
        ret.put("gender", user.getGender());
        ret.put("signature", user.getSignature());
        ret.put("cover", user.getCoverUrl());
        ret.put("phone", user.getPhone());

        return RespUtil.OKResponse(ret);
    }



    @RequestMapping(value = "/vCard", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateVCard(@RequestBody JSONObject req) throws Exception {
        ReqUtil.NotNullParams(req, "type", "value");

        Integer type = req.getInteger("type");
        User user = TUser.getUser();

        switch (VCardInfoType.parse(type)) {
            case AVATAR:
                JSONObject avatar = req.getJSONObject("value");
                if (avatar == null) {
                    break;
                }

                String url1 = req.getString("avatar_url");
                String url2 = req.getString("avatar_url_origin");

                if (StringUtil.NullOrEmpty(url1) || StringUtil.NullOrEmpty(url2)) {
                    return RespUtil.ERRORResponse(405, "Invalid params");
                }

                user.setAvatarUrl(url1);
                user.setAvatarUrlOrigin(url2);
                userService.updateUserInfo(VCardInfoType.AVATAR, user);
                break;

            case NAME:
                String name = req.getString("value");
                if (StringUtil.NullOrEmpty(name)) {
                    break;
                }

                user.setName(name);
                userService.updateUserInfo(VCardInfoType.NAME, user);
                break;

            case GENDER:
                Integer gender = req.getInteger("value");
                if (gender == null) {
                    return RespUtil.ERRORResponse(405, "Invalid params");
                }

                user.setGender(gender);
                userService.updateUserInfo(VCardInfoType.GENDER, user);
                break;


            case AREA:
                String area = req.getString("value");
                if (StringUtil.NullOrEmpty(area)) {
                    return RespUtil.ERRORResponse(405, "Invalid params");
                }

                user.setArea(area);
                userService.updateUserInfo(VCardInfoType.AREA, user);
                break;

            case SIGNATURE:
                String signature = req.getString("value");
                if (StringUtil.NullOrEmpty(signature)) {
                    return RespUtil.ERRORResponse(405, "Invalid params");
                }

                user.setSignature(signature);
                userService.updateUserInfo(VCardInfoType.SIGNATURE, user);
                break;

            case COVER:
                String coverImg = req.getString("value");
                if (StringUtil.NullOrEmpty(coverImg)) {
                    return RespUtil.ERRORResponse(405, "Invalid params");
                }

                user.setCoverUrl(coverImg);
                userService.updateUserInfo(VCardInfoType.COVER, user);
                break;

            case INVALID: {
                return RespUtil.ERRORResponse(405, "error type");
            }
        }

        return RespUtil.OKResponse();
    }





}
