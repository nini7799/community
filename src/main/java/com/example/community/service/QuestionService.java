package com.example.community.service;

import com.example.community.dto.QuestionQueryDTO;
import com.example.community.mapper.QusetionExtMapper;
import com.example.community.model.QuestionExample;
import org.apache.commons.lang3.StringUtils;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QusetionExtMapper qusetionExtMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(String search,Integer page, Integer size) {

        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);

        Integer totalCount = qusetionExtMapper.countBySearch(questionQueryDTO);//拿到发布的文章总数

        if(totalCount % size ==0){
            totalPage =totalCount / size;


        }else {
            totalPage = totalCount / size +1 ;
        }


        if(page<1){
            page=1;
        }

        if(page>totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        //开始数：size*(page-1)
        Integer offset = size*(page-1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = qusetionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question:questions){
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
         /*  古老方法： questionDTO.setId(question.getId()); */
         /*新方法如下，可以将question的所有属性快速拷贝到questionDTO的属性中*/
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);





        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
       Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);

        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.create(question);
        }else{
            //更新
           questionMapper.update(question);
        }
    }


    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();


        Integer totalPage;
        Integer totalCount = questionMapper.countByuserId(userId);//拿到发布的文章总数

        if(totalCount % size ==0){
            totalPage =totalCount / size;


        }else {
            totalPage = totalCount / size +1 ;
        }


        if(page<1){
            page=1;
        }

        if(page>totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);


        //开始数：size*(page-1)
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.listByuserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question:questions){
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            /*  古老方法： questionDTO.setId(question.getId()); */
            /*新方法如下，可以将question的所有属性快速拷贝到questionDTO的属性中*/
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);





        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public void incView(Long id) {

    }
}
