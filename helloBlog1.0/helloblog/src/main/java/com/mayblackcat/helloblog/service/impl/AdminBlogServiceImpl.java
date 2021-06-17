package com.mayblackcat.helloblog.service.impl;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.mayblackcat.helloblog.dao.AdminBlogDao;
import com.mayblackcat.helloblog.dao.AdminDao;
import com.mayblackcat.helloblog.dao.AdminTypeDao;
import com.mayblackcat.helloblog.entity.Blog;

import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.exception.NotFoundException;
import com.mayblackcat.helloblog.util.MyBeanUtils;
import com.mayblackcat.helloblog.vo.ShowBlog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminBlogServiceImpl {

    @Autowired
    private AdminBlogDao adminBlogDao;

    @Autowired
    private AdminTypeDao adminTypeDao;

    @Autowired
    private AdminDao adminDao;

    //查询推荐博客
    public List<Blog> getRecommendBlog(){

        return adminBlogDao.getRecommendBlog();
    }

    //查询热度博客
    public List<Blog> getHotBlog(){

        return adminBlogDao.getHotBlog();
    }

    //按标题内容搜索博客
    public List<Blog> getSearchBlog(String keyword){


        List<Blog> list=adminBlogDao.getSearchByTitle(keyword);
        return list;
    }

    //更新访问量
    public void updateViews(Blog blog){
        adminBlogDao.updateViews(blog);
    }

    //新增
    @Transactional
    public int addBlog(Blog blog){

        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);


        return adminBlogDao.addBlog(blog);
    }

    public List<ShowBlog> getShowBlog(){
        List<Blog> allBlog=adminBlogDao.getAllBlog();

        return getShowBlog(allBlog);
    }

    //获取博客展示集合类
    private List<ShowBlog> getShowBlog(List<Blog> blogList){

        List<ShowBlog> showBlogs=new ArrayList<>();


        for (Blog blog:blogList) {
            BlogUser user=adminDao.getAvatarById(blog.getUserId());
            ShowBlog sbs=new ShowBlog();
            sbs.setUserAvatar(user.getAvatar());
            sbs.setUserName(user.getUsername());
            sbs.setTypeName(blog.getTypeName());
            sbs.setUpdateTime(blog.getUpdateTime());
            sbs.setViews(blog.getViews());
            sbs.setBlogPicture(blog.getBlogPicture());
            sbs.setTitle(blog.getTitle());
            sbs.setId(blog.getId());
            sbs.setDescription(blog.getDescription());
            showBlogs.add(sbs);
        }
        return showBlogs;
    }

    //更新
    @Transactional
    public int updateBlog(Blog blog){
        Blog b1=adminBlogDao.getBlogById(blog.getId());
        if (b1==null){
            throw new NotFoundException("不存在该博客");
        }
        BeanUtils.copyProperties(blog,b1, MyBeanUtils.getNuLLPropertyNames(blog));

        b1.setUpdateTime(new Date());

       return adminBlogDao.updateBlog(b1);
    }

    @Transactional
    public void deleteBlog(Long id){
        adminBlogDao.deleteBlog(id);
    }

    @Transactional
    public List<Blog> getAllBlog(){
        return adminBlogDao.getAllBlog();
    }

    public List<Blog> getTimeBlog(){
        return adminBlogDao.getTimeAllBlog();
    }

    public List<Blog> getAllBlogByTypeId(Long typeId){
        return adminBlogDao.getAllBlogByTypeId(typeId);
    }

    @Transactional
    public Blog getBlogById(Long id){
        return adminBlogDao.getBlogById(id);
    }


    @Nullable
    //博客列表展示查询
    public ShowBlog getShowBlog(Long blogId,Long typeId){

            
        ShowBlog showBlog = adminBlogDao.getShowBlog(blogId);
        showBlog.setTypeName(adminTypeDao.getNameById(typeId));

        return showBlog;
    }

    //按标题和类型搜索
    public List<Blog> Search(String title, Long typeId) {
        if (title.trim().isEmpty()) {
            if (typeId != null) {
                return adminBlogDao.SearchByType(typeId);
            } else {
                return adminBlogDao.getAllBlog();
            }
        } else if (typeId != null) {
            return adminBlogDao.searchBlog(title, typeId);
        } else if (typeId == null){
            return adminBlogDao.SearchByTitle(title);
        }
        return null;
    }

}
