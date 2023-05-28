package com.example.community.mapper;


import com.example.community.model.Comment;
import com.example.community.model.CommentExample;
import com.example.community.model.User;
import com.example.community.model.UserExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
@Controller
public interface UserMapper {

//     @Insert("insert into user1 {name,account_id,token,gmt_create,gmt_modified,avatar_url} values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
     void insert(User user);
     
     @Select("select * from user1 where token =#{token}")
     User findByToken(@Param("token") String token);

     @Select("select * from user1 where id =#{id}")
     User findById(@Param("id") Long id);

     @Select("select * from user1 where account_id =#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

     @Update("update user1 set name =#{name},token=#{token},gmt_modified = #{gmtModified},avatar_url=#{avatarUrl} where id =#{id}")
     void update(User user);

    List<User> selectByExample(UserExample example);

}