package com.mayblackcat.helloblog.controller;

import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.service.impl.AdminBlogServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTypeServiceImpl;
import com.mayblackcat.helloblog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    @GetMapping("/blogs")
    public String blog(){
        return "blog";
    }


    @GetMapping("/tag")
    public String tag(){
        return "tag";
    }
}
