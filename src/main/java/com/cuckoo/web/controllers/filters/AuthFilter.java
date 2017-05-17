package com.cuckoo.web.controllers.filters;


import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.service.AccountService;
import com.cuckoo.web.utils.StringUtil;
import com.cuckoo.web.utils.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tanmq on 2017/2/27.
 */

@WebFilter(filterName  = "authFilter",
           urlPatterns = {"/api/v1/account/signOut",
                          "/api/v1/feed/*",
                          "/api/v1/user/*",
                          "/api/v1/friend/*"})
public class AuthFilter implements Filter{


    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    AccountService accountService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String authCode = request.getHeader("Authorization");
        String date = request.getHeader("Date");

        JSONObject object = new JSONObject();
        object.put("code", 406);
        if (StringUtil.NullOrEmpty(authCode) || StringUtil.NullOrEmpty(date)) {
            object.put("msg", "missing headers!");
            servletResponse.getWriter().print(object.toJSONString());
            return;
        }

        if (!doAuth(authCode, date)) {
            object.put("msg", "auth failed!");
            servletResponse.getWriter().print(object.toJSONString());
            return;
        }

        logger.info("Request path : {}", request.getPathInfo());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean doAuth(String authCode, String date) {
        String[] tokens = authCode.split(":");
        if (tokens.length < 3) {
            return false;
        }

        Integer device = Integer.valueOf(tokens[0]);
        Long uid       = Long.valueOf(tokens[1]);
        String code    = tokens[2];

        User user = accountService.auth(uid, device, code, date);
        if (user == null) {
            return false;
        }

        user.setDevice(device);
        TUser.setUser(user);

        return true;
    }
}
