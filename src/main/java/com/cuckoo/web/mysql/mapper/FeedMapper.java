package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.Feed;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
public interface FeedMapper {

    @Insert("insert into `feed` (`uid`,`title`,`coverImg`,`desc`,`content`,`cts`,`uts`) values " +
            "(#{f.uid}, #{f.title}, #{f.coverImg}, #{f.desc}, #{f.content}, #{f.cts} , #{f.uts})")
    @Options(useGeneratedKeys = true, keyProperty = "f.id")
    public void insertFeed(@Param("f") Feed feed);


    @Update("update `feed` set `title`=#{f.title}, `coverImg`=#{f.coverImg}, `desc`=#{f.desc}, " +
            "`content`=#{f.content}, `uts`=now() where `id`=#{f.id}")
    public void updateFeed(@Param("f")Feed feed);

    @Update("update `feed` set `status`=0 where `id`=#{id}")
    public void deleteFeed(@Param("id")long id);

    @Select("select * from `feed` where `uid`=#{uid} and `status`=1 order by `cts` desc limit #{offset}, #{size}")
    public List<Feed> getUserFeeds(@Param("uid")long uid, @Param("offset")int offset, @Param("size")int size);

    @Select("select * from `feed` where id=#{id}")
    public Feed getFeed(@Param("id")long id);

}
