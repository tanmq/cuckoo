package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.User;
import org.apache.ibatis.annotations.*;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * Created by tanmq on 2017/2/26.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getALl();

    @Select("select count(*) from user where `status`=1")
    public Integer countUsers();

    @Insert("insert into user (`name`,`phone`,`avatarUrl`, `avatarUrlOrigin`, `gender`,`passwd`,`salt`,`cts`,`uts`) " +
            "values (#{u.name}, #{u.phone}, #{u.avatarUrl}, #{u.avatarUrlOrigin}, #{u.gender}, #{u.passwd}, #{u.salt}, now(), now())")
    public void insertUser(@Param("u")User user);


    @Select("select * from user where `phone` =  #{phone} and `status` = 1")
    public User getUserByPhone(@Param("phone") String phone);

    @Select("select * from user where `name` = #{name} and `status` = 1")
    public User getUserByName(@Param("name") String name);

    @Select("select * from user where `id` = #{id} and `status` = 1")
    public User getUserById(@Param("id") Long id);

    @Update("update user set `name`=#{name}, `uts`=now() where `id`= #{id}")
    public void updateUserName(@Param("id")long id, @Param("name")String name);

    @Update("update user set `avatarUrl`=#{avatarUrl}, `avatarUrlOrigin`=#{avatarUrlOrigin}, `uts`=now() where `id`= #{id}")
    public void updateUserAvatar(@Param("id")long id, @Param("avatarUrl")String avatarUrl, @Param("avatarUrlOrigin")String avatarUrlOrigin);

    @Update("update user set `gender`=#{gender}, `uts`=now() where `id`=#{id}")
    public void updateUserGender(@Param("id")long id, @Param("gender")int gender);

    @Update("update user set `area`=#{area}, `uts`=now() where `id`=#{id}")
    public void updateUserArea(@Param("id")long id, @Param("area")String area);

    @Update("update user set `signature`=#{signature}, `uts`=now() where `id`=#{id}")
    public void updateUserSignature(@Param("id")long id, @Param("signature")String signature);

    @Update("update user set `coverUrl`=#{coverUrl}, `uts`=now() where `id`=#{id}")
    public void updateUserCoverImg(@Param("id")long id, @Param("coverUrl")String coverUrl);


}
