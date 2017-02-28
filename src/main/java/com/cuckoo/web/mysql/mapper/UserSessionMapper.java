package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.UserSession;
import org.apache.ibatis.annotations.*;

/**
 * Created by tanmq on 2017/2/27.
 */
@Mapper
public interface UserSessionMapper {

    @Insert("insert into user_session (`uid`,`sessionId`, `device`, `cts`, `uts`) values (#{uid}, #{sessionId}, #{device}, now(), now())")
    public void insertSession(@Param("uid") Long uid, @Param("sessionId") String sessionId, @Param("device")Integer device);

    @Select("select * from user_session where `sessionId`=#{sessionId} and status=1")
    public UserSession getSession(@Param("sessionId") String sessionId);

    @Update("update user_session set `status`=0, `uts`=now() where `uid`=#{uid} and `device`=#{device} and `status`=1")
    public void expireSession(@Param("uid") Long uid, @Param("device")Integer device);

    @Select("select * from user_session where `uid`=#{uid} and `status`=1 and `device`=#{device}")
    public UserSession getSessionByUid(@Param("uid") Long uid, @Param("device")Integer device);

}
