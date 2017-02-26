package com.cuckoo.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.exceptions.ParamException;

/**
 * Created by tanmq on 2017/2/26.
 */
public class ReqUtil {

    /**
     * 检测参数携带与否
     * @param req
     * @param keys
     * @throws ParamException
     */
    public static void NotNullParams(JSONObject req, String ... keys) throws ParamException{
        if (keys == null || keys.length == 0) {
            return;
        }

        for (int i = 0; i < keys.length; i ++) {
            String param = keys[i];
            if (req.get(param) == null) {
                throw new ParamException(400, param + " can not be null!");
            }
        }
    }



}
