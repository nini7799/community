package com.example.community.service;

import com.example.community.dto.CommentDTO;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.exception.CustomizeErroCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.QusetionExtMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QusetionExtMapper qusetionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
      if(comment.getParentId()==null || comment.getParentId()==0){
          throw  new CustomizeException(CustomizeErroCode.TARGET_PARAM_NOT_FOUND);
      }

      if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
          throw  new CustomizeException(CustomizeErroCode.TYPE_PARAM_WRONG);
      }

      if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
          //回复评论
          Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
          if(dbcomment==null){
              throw  new CustomizeException(CustomizeErroCode.COMMENT_NOT_FOUND);
          }

          commentMapper.insert(comment);

      }else{
          //回复问题
          Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
          if(question==null){
              throw  new CustomizeException(CustomizeErroCode.QUESTION_NOT_FOUND);

          }


          commentMapper.insert(comment);
          question.setCommentCount(1);
          qusetionExtMapper.inCommentCount(question);
      }
    }

    public List<CommentDTO> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }

        //获取去重的评论人
        Set<Long> commentors = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentors);

         //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                        .andIdIn(userIds);

        //转换comment为CommentDTO
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long,User> userMap =  users.stream().collect(Collectors.toMap(user->user.getId(), user->user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
                return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;

    }
}
