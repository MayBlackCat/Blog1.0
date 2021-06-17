package com.mayblackcat.helloblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackgroundController {
    @GetMapping("/background")
    public String background(){
        return "background";
    }
}
