package com.cuckoo.web.mysql.dao;

import com.cuckoo.web.mysql.ddl.UserSession;
import com.cuckoo.web.mysql.mapper.UserSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tanmq on 2017/2/27.
 */
@Component
public class UserSessionDao {

    @Autowired
    UserSessionMapper userSessionMapper;

    public void addSession(Long uid, String sessionId, Integer device) {
        userSessionMapper.insertSession(uid, sessionId, device);
    }

    public UserSession getSession(String sessionId) {
        return userSessionMapper.getSession(sessionId);
    }

    public void expireSession(long uid, Integer device) {
        userSessionMapper.expireSession(uid, device);
    }

    public UserSession getSessionByUid(Long uid, Integer device) {
        if (uid == null || uid == 0) {
            return null;
        }
        return userSessionMapper.getSessionByUid(uid, device);
    }
}
