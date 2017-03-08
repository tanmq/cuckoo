package com.cuckoo.web.utils;

/**
 * Created by tanmq on 2017/3/8.
 */
public class IntegerUtil {

    public static boolean NullORZero(Integer value) {
        if (value == null || value == 0) {
            return true;
        }

        return false;
    }

}
