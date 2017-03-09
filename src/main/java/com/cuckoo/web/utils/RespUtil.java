package com.cuckoo.web.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.User;

/**
 * Created by tanmq on 2017/2/26.
 */
public class RespUtil {

    /**
     * 操作成功返回
     * @return
     */
    public static JSONObject OKResponse() {
        JSONObject resp = new JSONObject();
        resp.put("code", 200);
        resp.put("msg", "OK");

        return resp;
    }

    /**
     * 成功获取数据返回
     * @param data
     * @return
     */
    public static JSONObject OKResponse(JSONObject data) {
        JSONObject resp = new JSONObject();
        resp.put("code", 200);
        resp.put("data", data);

        return resp;
    }

    /**
     * 成功返回数组数据
     * @param array
     * @return
     */
    public static JSONObject OKResponse(JSONArray array) {
        JSONObject resp = new JSONObject();
        resp.put("code", 200);
        resp.put("data", array);

        return resp;
    }

    /**
     * 失败返回
     * @param code
     * @param msg
     * @return
     */
    public static JSONObject ERRORResponse(int code, String msg) {
        JSONObject resp = new JSONObject();
        resp.put("code", code);
        resp.put("msg", msg);

        return resp;
    }


    public static JSONObject buildUserResponse(User user, String sessionId) {
        JSONObject data = new JSONObject();
        data.put("name", user.getName());
        data.put("uid", user.getId());
        data.put("phone", user.getPhone());
        data.put("gender", user.getGender());
        data.put("avatar_url", user.getAvatarUrl());
        data.put("session_id", sessionId);

        return OKResponse(data);
    }





}
