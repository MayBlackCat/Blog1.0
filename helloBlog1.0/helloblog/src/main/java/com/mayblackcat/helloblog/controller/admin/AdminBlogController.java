package com.mayblackcat.helloblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.service.impl.AdminBlogServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTagServiceImpl;
import com.mayblackcat.helloblog.service.impl.AdminTypeServiceImpl;
import com.mayblackcat.helloblog.vo.ShowBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping(value = "/admin",produces = "application/json;charset=utf-8")
public class AdminBlogController {

    @Autowired
    private AdminBlogServiceImpl adminBlogService;
    @Autowired
    private AdminTypeServiceImpl adminTypeService;
    @Autowired
    private AdminTagServiceImpl adminTagService;

    //博客管理首页面
    @GetMapping("/blogs")
    public String blogsPage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){

        PageHelper.startPage(pageNum,5);
        //设置博客列表
        List<Blog> allBlogs=adminBlogService.getAllBlog();


        PageInfo<Blog> blogPage=new PageInfo<>(allBlogs);

        PageHelper.startPage(pageNum,5);
        List<BlogType> allType=adminTypeService.getListType();
        //获取分页结果并设置到视图中
        PageInfo<BlogType> res=new PageInfo<>(allType);

        model.addAttribute("types",res);
        model.addAttribute("page",blogPage);
        return "admin/blogs";
    }

    //搜索博客
    @PostMapping("/blogs/search")
    public String search(@RequestParam String title,@RequestParam Long typeId, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<Blog> blogBySearch = adminBlogService.Search(title,typeId);
        //将数据库查到对应type赋值给blogBySearch
        for (Blog blog:blogBySearch) {
            blog.setType(adminTypeService.getType(blog.getTypeId()));
        }
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("page", pageInfo);


        return "admin/blogs :: blogList";//前面指的是请求的html页面路径名称，后面指的模板名
    }

    //跳转到新增页面
    @GetMapping("/blogs/add")
    public ModelAndView addPage(Model model){
        ModelAndView modelAndView=new ModelAndView();
        //初始化设置
        model.addAttribute("types",adminTypeService.getListType());
        model.addAttribute("tags",adminTagService.getListTags());
        //默认初始化一个blog对象作用：防止与修改共用一个页面时，返回一个页面值时报错
        model.addAttribute("blog",new Blog());
        modelAndView.setViewName("admin/blogs-input");
        return modelAndView;
    }

    /**
     *
     * @param blog 前端传给后台博客数据对象
     * @param redirectAttributes    处理消息
     * @param session 博客绑定对应用户
     * @return
     */
    //修改博客
    @PostMapping("/blogs/{id}")
    public String modifyBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        //拿到当前用户信息
        blog.setUser((BlogUser) session.getAttribute("user"));
        //为发布博客设置类型Id
        blog.setType(adminTypeService.getType(blog.getType().getId()));
        //设置类型名
        blog.setTypeName(adminTypeService.getNameById(blog.getType().getId()));
        //为发布博客设置标签集
        blog.setTags(adminTagService.getTagIds(blog.getTagIds()));
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b=0;
        if (blog.getId()!=null){

            b=adminBlogService.updateBlog(blog);

        }

        if (b!=0) {
            redirectAttributes.addFlashAttribute("message", "修改成功！");
        }else {
            redirectAttributes.addFlashAttribute("message", "修改失败！");
        }
        return "redirect:/admin/blogs";

    }


    //发布博客
    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        //拿到当前用户信息
        blog.setUser((BlogUser) session.getAttribute("user"));
        //为发布博客设置类型Id
        blog.setType(adminTypeService.getType(blog.getType().getId()));
        //设置类型名
        blog.setTypeName(adminTypeService.getNameById(blog.getType().getId()));
        //为发布博客设置标签集
        blog.setTags(adminTagService.getTagIds(blog.getTagIds()));
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b=0;
        if (blog.getId()==null){
            //存储博客数据
            b=adminBlogService.addBlog(blog);
        }

        if (b!=0) {
            redirectAttributes.addFlashAttribute("message", "新增博客成功！");
        }else {
            redirectAttributes.addFlashAttribute("message", "新增博客失败！");
        }
        return "redirect:/admin/blogs";

    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes redirectAttributes){
        adminBlogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    //    跳转编辑修改文章
    @GetMapping("/blogs/{id}/modify")
    public String modifyBlog(@PathVariable Long id, Model model) {
        Blog blog = adminBlogService.getBlogById(id);
        model.addAttribute("types", adminTypeService.getListType());
        model.addAttribute("tags", adminTagService.getListTags());
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

}
