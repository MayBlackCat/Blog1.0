package com.mayblackcat.helloblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mayblackcat.helloblog.dao.AdminDao;
import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.entity.BlogTag;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.exception.NotFoundException;

import com.mayblackcat.helloblog.service.impl.AdminBlogServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTagServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTypeServiceImpl;
import com.mayblackcat.helloblog.util.MarkdownUtils;
import com.mayblackcat.helloblog.vo.ShowBlog;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//首页控制器
@Controller //将controller层类注册到bean中
public class IndexController {

    @Autowired
    private AdminBlogServiceImpl adminBlogService;
    @Autowired
    private AdminTypeServiceImpl adminTypeService;
    @Autowired
    private AdminTagServiceImpl adminTagService;
    @Autowired
    private AdminServiceImpl adminService;


    //首页展示
    @GetMapping("/")
    public String index(@RequestParam(defaultValue ="1",value = "pageNum")Integer pageNum, Model model){


        PageHelper.startPage(pageNum, 6);
        List<Blog> allBlog = adminBlogService.getAllBlog();

        List<BlogType> allType=adminTypeService.getListType();
        List<BlogTag> allTag=adminTagService.getListTags();
        List<Blog> allRecommend=adminBlogService.getRecommendBlog();
        List<Blog> allHot=adminBlogService.getHotBlog();
        //这里不能改变数据库查询对象，只能通过增强for去给原有对象设值。原因是由于如果改变对象重新设置值会导致session属性发生改变，session有些方法会直接操作数据库
        for (Blog b : allBlog) {
            b.setType(adminTypeService.getType(b.getTypeId()));
            b.setUser(adminService.getAvatarById(b.getUserId()));
        }


        PageInfo<Blog> resBlog=new PageInfo<>(allBlog);

        model.addAttribute("showBlog",resBlog);
        model.addAttribute("showType",allType);
        model.addAttribute("showTag",allTag);
        model.addAttribute("recommend",allRecommend);
        model.addAttribute("hot",allHot);


        return "index";
    }


    //每篇博客展示
    @GetMapping("/blog/{id}")
    public String blogPage(@PathVariable Long id,Model model){

        Blog blog = adminBlogService.getBlogById(id);
        blog.setType(adminTypeService.getType(blog.getTypeId()));
        blog.setUser(adminService.getAvatarById(blog.getUserId()));
        //设置标签集
        blog.setTags(adminTagService.getTagIds(blog.getTagIds()));


        //访问量统计
        blog.setViews(blog.getViews()+1);
        adminBlogService.updateViews(blog);

        //  单一处理MarkDown文本形成html内容,新建一个博客对象作存储避免对数据库更改
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));


        model.addAttribute("blog",b);
        return "blog";
    }

    //搜索博客
    @PostMapping("/search")
    public String searchBlogPage(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam String query,Model model){
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = adminBlogService.getSearchBlog(query);
        for (Blog b : allBlog) {
            b.setType(adminTypeService.getType(b.getTypeId()));
            b.setUser(adminService.getAvatarById(b.getUserId()));
        }
        PageInfo<Blog> lists = new PageInfo<>(allBlog);
        model.addAttribute("searchBlog",lists);
        model.addAttribute("query",query);
        return "search";
    }




}
