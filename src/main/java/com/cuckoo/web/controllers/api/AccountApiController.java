package com.cuckoo.web.controllers.api;


import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.exceptions.ParamException;
import com.cuckoo.web.utils.ReqUtil;
import com.cuckoo.web.utils.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanmq on 2017/2/26.
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController {

    private static Logger logger = LoggerFactory.getLogger(AccountApiController.class);

    //用户登录
    @RequestMapping(value = "/signIn", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JSONObject signIn(@RequestBody JSONObject account) throws Exception{
        ReqUtil.NotNullParams(account, "account", "password");
        return RespUtil.OKResponse();
    }

    //用户注册
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public JSONObject signUp() {
        return RespUtil.OKResponse();
    }

    //用户推出
    @RequestMapping(value = "/sigOut", method = RequestMethod.POST)
    public JSONObject signOut() {
        return RespUtil.OKResponse();
    }


}
