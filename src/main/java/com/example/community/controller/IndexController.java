package com.example.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;  //annotation注解

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index"; //此时自动返回下面index.html这个模板，在模板目录resources-templates里找

    }
}