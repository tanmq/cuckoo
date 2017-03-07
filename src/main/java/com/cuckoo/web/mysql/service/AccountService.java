package com.cuckoo.web.mysql.service;

import com.cuckoo.web.mysql.dao.UserDao;
import com.cuckoo.web.mysql.dao.UserSessionDao;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.ddl.UserSession;
import com.cuckoo.web.utils.EncodeUtil;
import com.cuckoo.web.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by tanmq on 2017/2/27.
 */
@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    UserSessionDao userSessionDao;


    private void addNewUser(User user) {
        userDao.insertUser(user);
    }

    /**
     * 有效账户返回用户信息，否则返回null
     * @param account
     * @param passwd
     * @return
     */
    public User signIn(String account, String passwd) {
        if (StringUtil.NullOrEmpty(account) || StringUtil.NullOrEmpty(passwd)) {
            logger.warn("Params is null. account={} and passwd={}", account, passwd);
            return null;
        }

        User user = getUser(account);
        if (user == null) {
            return null;
        }

        if (validatePasswd(passwd, user)) {
            return user;
        }

        return null;
    }

    public boolean addNewUser(@NotNull String name, @NotNull String phone,
                              @NotNull String avatarUrl, @NotNull Integer gender, @NotNull String pass) {
        if (userExist(phone)) {
            return false;
        }

        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setAvatarUrl(avatarUrl);
        user.setGender(gender);

        String salt     = EncodeUtil.newSalt(phone);
        String passwd   = EncodeUtil.MD5(pass + salt);

        user.setSalt(salt);
        user.setPasswd(passwd);

        addNewUser(user);

        return true;
    }



    public boolean userExist(String account) {
        if (getUser(account) == null) {
            return false;
        }

        return true;
    }

    private boolean validatePasswd(@NotNull String passwd, @NotNull User user) {
        String salt = user.getSalt();
        String encode = EncodeUtil.MD5(passwd + salt);
        if (encode.equals(user.getPasswd())) {
            return true;
        }

        return false;
    }


    /**
     * 根据手机或者邮箱获取用户
     * @param account
     * @return
     */
    private User getUser(String account) {
        return userDao.getUserByPhone(account);
    }


    public boolean userExistByName(String name) {
        return userDao.getUserByName(name) != null;
    }

    public boolean userExistByPhone(String phone) {
        return userExist(phone);
    }


    public String buildSession(User user, Integer device) {
        UserSession userSession = userSessionDao.getSessionByUid(user.getId(), device);

        //踢掉旧设备登录信息
        if (userSession != null) {
            userSessionDao.expireSession(user.getId(), device);
        }

        String sessionId = EncodeUtil.newSessionId();
        userSessionDao.addSession(user.getId(), sessionId, device);

        return sessionId;
    }


    public User auth(Long uid, Integer device, String code, String date) {
        if (StringUtil.NullOrEmpty(code)) {
            return null;
        }

        UserSession userSession = userSessionDao.getSessionByUid(uid, device);
        if (userSession == null) {
            return null;
        }

        User user = userDao.getUserById(userSession.getUid());

        String authCode = EncodeUtil.MD5(uid+device+date+userSession.getSessionId());
        if (authCode.equals(code)) {
            return user;
        }

        return null;
    }

    public void expireSession(long uid, Integer device) {
        userSessionDao.expireSession(uid, device);
    }


}
