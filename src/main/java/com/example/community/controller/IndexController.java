package com.example.community.controller;

import com.example.community.model.User;
import com.example.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("token")){
                String token =cookie.getValue();
                User user =userMapper.findByToken(token);
                if(user != null){
                  request.getSession().setAttribute("user",user);
                }
                break;


            }
        }


        return "index"; //此时自动返回下面index.html这个模板，在模板目录resources-templates里找

    }
}