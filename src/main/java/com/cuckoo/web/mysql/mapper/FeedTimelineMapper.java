package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.Feed;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by tanmq on 2017/3/8.
 */
public interface FeedTimelineMapper {


    @Select("select * from `feed` where id in " +
            "(select `fid` from `feed_timeline` where `uid`=#{uid})" +
            " and `status`=1  order by `cts` desc limit #{offset}, #{size}")
    public List<Feed> getTimeLine(@Param("uid")long uid,
                                  @Param("offset")int offset,
                                  @Param("size")int size);

    @Insert("insert into `feed_timeline` (`uid`,`fid`,`authorId`,`cts`) values (#{uid}, #{fid}, #{aid}, #{cts})")
    public void insertTimelineItem(@Param("uid")long uid, @Param("fid")long fid, @Param("aid")long authorId, @Param("cts")Date cts);

    @Delete("delete from `feed_timeline` where `uid`=#{uid} and `authorId`=#{aid}")
    public void hideUserFeeds(@Param("uid")long uid, @Param("aid")long authorId);




}
