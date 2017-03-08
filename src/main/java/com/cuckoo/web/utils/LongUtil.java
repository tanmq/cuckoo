package com.cuckoo.web.utils;

/**
 * Created by tanmq on 2017/3/8.
 */
public class LongUtil {


    public static boolean NullORZero(Long value) {
        if (value == null || value == 0) {
            return true;
        }

        return false;
    }
}
