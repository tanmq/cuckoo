package com.cuckoo.web.mysql.ddl;

import java.util.Date;

/**
 * Created by tanmq on 2017/3/8.
 */
public class UserFollow {

    private long id;

    private long uid;

    private long followUid;

    private Date cts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getFollowUid() {
        return followUid;
    }

    public void setFollowUid(long followUid) {
        this.followUid = followUid;
    }

    public Date getCts() {
        return cts;
    }

    public void setCts(Date cts) {
        this.cts = cts;
    }
}
