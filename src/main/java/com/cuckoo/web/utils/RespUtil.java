package com.cuckoo.web.utils;

import com.alibaba.fastjson.JSONObject;

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





}
