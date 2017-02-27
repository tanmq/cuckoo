package com.cuckoo.web.controllers.api;


import com.alibaba.fastjson.JSONObject;
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
        ReqUtil.NotNullParams(req, "name", "phone", "email", "avatar_url", "gender", "password");

        String name         = req.getString("name");
        String phone        = req.getString("phone");
        String email        = req.getString("email");
        String avatarUrl    = req.getString("avatar_url");
        String passwd       = req.getString("password");
        Integer gender      = req.getInteger("gender");

        boolean added = accountService.addNewUser(name, phone, email, avatarUrl, gender, passwd);
        if (added) {
            return RespUtil.OKResponse();
        }

        return RespUtil.ERRORResponse(405, "注册失败");

    }

    //用户退出
    @RequestMapping(value = "/sigOut", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject signOut(@RequestBody JSONObject req) throws Exception{
        User user = TUser.getUser();

        accountService.expireSession(user.getId(), user.getDevice());
        return RespUtil.OKResponse();
    }


}
