package com.example.community.mapper;

import com.example.community.dto.QuestionQueryDTO;
import com.example.community.model.Comment;
import com.example.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import com.example.community.model.Comment;
import com.example.community.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
@Mapper
@Controller
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);


    @Select("Select * from question limit #{offset},#{size} ")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where id = #{id}")
    Question getById(Long id);


    @Update("update question set title=#{title},description=#{description},gmt_modified = #{gmtModified},tag=#{tag} where id = #{id}")
    void update(Question question);

    @Select("select * from question where creator = #{userId} limit #{offset},#{size} ")
    List<Question> listByuserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByuserId(@Param(value = "userId") Long userId);

    Question selectByPrimaryKey(Long id);


}

