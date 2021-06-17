package com.mayblackcat.helloblog.controller;

import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.service.impl.AdminBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArchivesController {
    @Autowired
    private AdminBlogServiceImpl adminIndexService;

    @GetMapping("/archives")
    public String archives(Model model) {
        List<Blog> blogs = adminIndexService.getTimeBlog();
        model.addAttribute("blogs", blogs);
        return "archives";
    }
}
