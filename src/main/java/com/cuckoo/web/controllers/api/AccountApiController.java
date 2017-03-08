package com.cuckoo.web.controllers.api;


import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.controllers.enumutation.AccountField;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.AccountService;
import com.cuckoo.web.utils.ReqUtil;
import com.cuckoo.web.utils.RespUtil;
import com.cuckoo.web.utils.StringUtil;
import com.cuckoo.web.utils.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanmq on 2017/2/26.
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController {

    private static Logger logger = LoggerFactory.getLogger(AccountApiController.class);

    @Autowired
    AccountService accountService;


    @RequestMapping(value = "validate", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject validate(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "field", "value");

        int field       = req.getInteger("field");
        String value    = req.getString("value");

        AccountField accountField = AccountField.valOf(field);
        if (accountField == null) {
            return RespUtil.ERRORResponse(407, "Error field, field is undefined");
        }

        switch (accountField) {
            case NAME:
                if (accountService.userExistByName(value)) {
                    return RespUtil.ERRORResponse(407, "该昵称已经被使用，请更换另一个");
                }
                break;
            case PHONE:
                if (accountService.userExistByPhone(value)) {
                    return RespUtil.ERRORResponse(407, "该手机号已经注册！");
                }
                break;
        }

        return RespUtil.OKResponse();
    }

    //用户登录
    @RequestMapping(value = "/signIn", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JSONObject signIn(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "account", "password");

        String account = req.getString("account");
        String passwd  = req.getString("password");
        Integer device = req.getInteger("device");

        if (device == null ) {
            device = 0;
        }

        if (StringUtil.NullOrEmpty(account) || StringUtil.NullOrEmpty(passwd)) {
            return RespUtil.ERRORResponse(401, "账户名和密码不能为空.");
        }

        User user = accountService.signIn(account, passwd);
        if (user == null) {
            return RespUtil.ERRORResponse(400, "用户名或密码错误.");
        }

        String sessionId = accountService.buildSession(user, device);

        return RespUtil.buildUserResponse(user, sessionId);
    }

    //用户注册
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public JSONObject signUp(@RequestBody JSONObject req) throws Exception{
        ReqUtil.NotNullParams(req, "name", "phone", "avatar_url", "gender", "password");

        String name         = req.getString("name");
        String phone        = req.getString("phone");
        String avatarUrl    = req.getString("avatar_url");
        String passwd       = req.getString("password");
        Integer gender      = req.getInteger("gender");

        boolean added = accountService.addNewUser(name, phone, avatarUrl, gender, passwd);
        if (added) {
            return RespUtil.OKResponse();
        }

        return RespUtil.ERRORResponse(405, "注册失败");

    }

    //用户退出
    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject signOut() throws Exception{
        User user = TUser.getUser();

        accountService.expireSession(user.getId(), user.getDevice());
        return RespUtil.OKResponse();
    }


}
