package com.cuckoo.web.controllers.web;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by tanmq on 2017/2/28.
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    private static String code = "1234567890987654321";

    @Autowired
    UserDao userDao;


    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String dashboard(@Param("code")String code, HttpServletResponse response, Map<String, Object> model) throws Exception{
        if (code == null || !code.equals(code)) {
            response.sendRedirect("/");
        }

        model.put("count", userDao.countUsers());
        return "monitor/dashboard";
    }


    @RequestMapping(value = "userCount", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject userCount(@Param("code")String code, HttpServletResponse response) throws Exception{
        if (code == null || !code.equals(code)) {
            response.sendRedirect("/");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", userDao.countUsers());

        return jsonObject;
    }


}
