package com.mayblackcat.helloblog.controller;

import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.service.impl.AdminBlogServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTagServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TypesController {

    @Autowired
    private AdminTypeServiceImpl adminTypeService;

    @Autowired
    private AdminBlogServiceImpl adminBlogService;

    @Autowired
    private AdminServiceImpl adminService;

    //按分类展示博客
    @GetMapping("/types")
    public String type(Model model){
        List<Blog> blogList=adminBlogService.getAllBlog();

        for (Blog b : blogList) {
            b.setType(adminTypeService.getType(b.getTypeId()));
            b.setUser(adminService.getAvatarById(b.getUserId()));
        }
        model.addAttribute("pages", blogList);

        //
        List<BlogType> types = (adminTypeService.findAllType( adminTypeService.findLimitType() ));
        model.addAttribute("types", types);

        return "types";

    }

    //  由链接进入分类
    @GetMapping("/type/{id}")
    public String BlogByType(Model model, @PathVariable Long id) {
        List<Blog> allBlog = adminBlogService.getAllBlogByTypeId(id);
        for (Blog b : allBlog) {
            b.setType(adminTypeService.getType(b.getTypeId()));
            b.setUser(adminService.getAvatarById(b.getUserId()));
        }
        model.addAttribute("pages", allBlog);

        List<BlogType> types = adminTypeService.findAllType(adminTypeService.getListType());
        model.addAttribute("types", types);
        return "types";
    }
}
