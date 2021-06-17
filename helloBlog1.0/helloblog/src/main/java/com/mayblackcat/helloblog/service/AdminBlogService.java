package com.mayblackcat.helloblog.service;

import com.mayblackcat.helloblog.entity.Blog;

import com.mayblackcat.helloblog.vo.ShowBlog;
import org.springframework.lang.Nullable;
import java.util.List;

public interface AdminBlogService {


    List<Blog> getRecommendBlog();

    List<Blog> getSearchBlog(String keyword);


    int addBlog(Blog blog);
    List<ShowBlog> getShowBlog();

    List<ShowBlog> getShowBlog(List<Blog> blogList);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getAllBlog();

    List<Blog> getTimeBlog();

    List<Blog> getAllBlogByTypeId(Long typeId);

    Blog getBlogById(Long id);

    ShowBlog getShowBlog(Long blogId,Long typeId);

    List<Blog> Search(String title, Long typeId);

}
