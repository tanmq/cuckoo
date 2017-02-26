package com.cuckoo.web.controllers.handler;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.exceptions.ParamException;
import com.cuckoo.web.utils.RespUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tanmq on 2017/2/26.
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public JSONObject paramErrHandler(ParamException e) {
        return RespUtil.ERRORResponse(e.getCode(), e.getMsg());
    }


}
