package com.example.community.mapper;

import com.example.community.model.Comment;
import com.example.community.model.CommentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;

@Mapper
@Controller

public interface CommentMapper {

    long countByExample(CommentExample example);


    int deleteByExample(CommentExample example);


    int deleteByPrimaryKey(Long id);


    int insert(Comment record);


    int insertSelective(Comment record);


    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);


    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);


    int updateByPrimaryKeySelective(Comment record);


    int updateByPrimaryKey(Comment record);
}