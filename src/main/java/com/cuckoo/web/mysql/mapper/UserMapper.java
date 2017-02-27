package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by tanmq on 2017/2/26.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getALl();


    @Insert("insert into user (`name`,`phone`,`email`,`avatar_url`,`gender`,`passwd`,`salt`) " +
            "values (#{u.name}, #{u.phone}, #{u.email}, #{u.avatarUrl}, #{u.gender}, #{u.passwd}, #{u.salt})")
    public void insertUser(@Param("u")User user);


    @Select("select * from user where `phone` =  #{phone} and `status` = 1")
    public User getUserByPhone(@Param("phone") String phone);

    @Select("select * from user where `email` = #{email} and `status` = 1")
    public User getUserByEmail(@Param("email") String email);

    @Select("select * from user where `id` = #{id} and `status` = 1")
    public User getUserById(@Param("id") Long id);



}
