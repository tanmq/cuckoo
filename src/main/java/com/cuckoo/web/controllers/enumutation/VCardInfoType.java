package com.cuckoo.web.controllers.enumutation;

/**
 * Created by tanmq on 2017/4/2.
 */
public enum VCardInfoType {
    INVALID(-1),
    AVATAR(0),
    NAME(1),
    GENDER(2),
    AREA(3),
    SIGNATURE(4),
    COVER(5);

    private int type;

    private VCardInfoType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }


    public static VCardInfoType parse(int type) {
        switch (type) {
            case 0:
                return AVATAR;
            case 1:
                return NAME;
            case 2:
                return GENDER;
            case 3:
                return AREA;
            case 4:
                return SIGNATURE;
            case 5:
                return COVER;
            default:
                return INVALID;
        }
    }

}
