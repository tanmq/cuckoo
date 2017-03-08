package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.UserFollow;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
@Mapper
public interface UserFollowMapper {


    @Insert("insert into `user_follow` (`uid`,`followUid`,`cts`) values (#{uid}, #{followUid}, now())")
    public void follow(@Param("uid")long uid, @Param("followUid")long followUid);

    @Delete("delete from `user_follow` where `uid`=#{uid} and `followUid`=#{followUid}")
    public void unFollow(@Param("uid")long uid, @Param("followUid")long followUid);

    @Select("select * from `user_follow` where `followUid`=#{uid}")
    public List<UserFollow> getUserFollowers(@Param("uid")long uid);

    @Select("select * from `user_follow` where `uid`=#{uid}")
    public List<UserFollow> getFollowingUsers(@Param("uid")long uid);


}
