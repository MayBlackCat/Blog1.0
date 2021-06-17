package com.mayblackcat.helloblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
