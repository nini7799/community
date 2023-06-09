package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {



        private Long id;
        private Long parentId;
        private String content;
        private Integer type;
        private Long commentator;
        private Long gmtCreate;
        private Long gmtModified;
        private Long likeCount;
        private User user;
    }

