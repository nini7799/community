package com.example.community.exception;

public enum CustomizeErroCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
  //  SYS_ERROR(2004,"服务器冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不换个试试"),;

    @Override
    public String getMessage(){return  message;}

    @Override
    public Integer getCode() {
        return code;
    }


    private Integer code;
    private String message;


   CustomizeErroCode(Integer code, String message){
       this.message = message;
       this.code = code;
   }



}
