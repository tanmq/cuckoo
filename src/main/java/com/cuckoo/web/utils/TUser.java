package com.cuckoo.web.utils;

import com.cuckoo.web.mysql.ddl.User;

/**
 * Created by tanmq on 2017/2/27.
 */
public class TUser {

    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static User getUser() {
        return threadLocal.get();
    }

}
