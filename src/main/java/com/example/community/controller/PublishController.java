package com.example.community.controller;


import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {



   @Autowired
   private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish/{id}") //网址是http://localhost:8887/publish/6
    private String edit(@PathVariable(name="id") Long id,
                        Model model){

        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        return "publish";  //返回页面是publish.html
    }

    @GetMapping("/publish")  /* publish页面 */
    public String publish(){

        return "publish";
    }

    @PostMapping ("/publish") /* 点击发布按钮后，如果发布失败，则跳转回发布页面 */
    public String dopublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title ==null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description ==null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }

        if(tag ==null || tag == ""){
            /*没有标签不方便后面推进*/
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length != 0){


        for(Cookie cookie :cookies){
            if(cookie.getName().equals("token")){
                String token =cookie.getValue();
                user =userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
          }
        }
        if(user ==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
    /*    question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate()); */
        question.setId(id);

        questionService.createOrUpdate(question);

        return "redirect:/";
    }

}
