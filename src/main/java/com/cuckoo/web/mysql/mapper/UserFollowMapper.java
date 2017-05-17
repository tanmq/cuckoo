package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.mysql.ddl.UserFollow;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Mapper
public interface UserFollowMapper {


    @Insert("insert ignore into `user_follow` (`uid`,`followUid`,`cts`) values (#{uid}, #{followUid}, now())")
    public void follow(@Param("uid")long uid, @Param("followUid")long followUid);

    @Delete("delete from `user_follow` where `uid`=#{uid} and `followUid`=#{followUid}")
    public void unFollow(@Param("uid")long uid, @Param("followUid")long followUid);

    @Select("select * from `user_follow` where `followUid`=#{uid}")
    public List<UserFollow> getUserFollowers(@Param("uid")long uid);

    @Select("select * from `user_follow` where `uid`=#{uid}")
    public List<UserFollow> getFollowingUsers(@Param("uid")long uid);

    @Select("select * from user where id in " +
            "(select `followUid` from `user_follow` where `uid`=#{uid}) " +
            "and `status` = 1 order by cts desc limit #{offset}, #{count}")
    public List<User> getFollowers(@Param("uid")long uid, @Param("offset")int offset, @Param("count")int count);


    @Select("select * from user where id in " +
            "(select `uid` from `user_follow` where `followUid`=#{uid}) " +
            "and `status` = 1 order by cts desc limit #{offset}, #{count}")
    public List<User> getFollowees(@Param("uid")long uid, @Param("offset")int offset, @Param("count")int count);


    @Select("select count(*) from user_follow where `uid`=#{uid} and `followUid`=#{targetUid}")
    public int hasFollow(@Param("uid")long uid, @Param("targetUid")long targetUid);


}
