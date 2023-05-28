package com.example.community.dto;

import lombok.Data;
import org.apache.el.parser.AstFalse;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious; //表示是否有向前按钮，默认为false，
    private boolean showFirstPage; //第一页按钮
    private boolean showNext;  //下一页
    private boolean showEndPage;  //最后一页按钮
    private Integer page;//当前按钮，当前页面数
    private List<Integer> pages = new ArrayList<>();
    public Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {



        this.totalPage =totalPage;
        this.page = page;


         /* 当前页为1，页面列表一共有7个
         则展示1,2,3,4,5,6,7
           当前页为2
           1,2,3,4,5,6,7
           3
           1,2,3,4,5,6,7
           4
           1,2,3，4,5,6,7
           5  【左边一定有三个，如果不是三个就把所有的补全】
           2,3,4,5,6,7，8
           6
           3,4,5,6,7,8,9
           7
           4,5,6,7,8,9,10
         */
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }

            if(page +i <= totalPage){
                pages.add(page+i);
            }
        }




        //是否展示跳转到上一页按钮
        if (page == 1){
            showPrevious = false;
        }else {
            showNext = true;
        }

        //是否展示跳转到下一页按钮
        if(page == totalPage){
           showNext = false;
        }else{
            showEndPage = true;
        }

        //是否展示跳转到第一页的按钮
        /*coutains(String str);判断是否存在某个字符
          如果页面列表中出现1，则不会出现跳转到第一页的按钮，否则出现 */
        if(pages.contains(1)){
           showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //是否展示跳转到最后一页的按钮
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }






}
