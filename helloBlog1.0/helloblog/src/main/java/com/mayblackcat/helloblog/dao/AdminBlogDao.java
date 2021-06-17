package com.mayblackcat.helloblog.dao;

import com.mayblackcat.helloblog.entity.Blog;
import com.mayblackcat.helloblog.vo.ShowBlog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminBlogDao {

    @Insert("insert into blog(blogPicture,title,content,flags,appreciate,commentable,createTime,updateTime,userId,typeId,tagIds,views,recommend,publish,published,description,typeName) " +
            "values(#{blogPicture},#{title},#{content},#{flags},#{appreciate},#{commentable},#{createTime},#{updateTime},#{userId},#{typeId},#{tagIds},#{views},#{recommend},#{publish},#{published},#{description},#{typeName}) ")
    int addBlog(Blog blog);

    @Update("update blog set blogPicture=#{blogPicture},title=#{title},content=#{content},flags=#{flags},appreciate=#{appreciate}," +
            "commentable=#{commentable},createTime=#{createTime},updateTime=#{updateTime},typeId=#{typeId},tagIds=#{tagIds},views=#{views},recommend=#{recommend},publish=#{publish},published=#{published},description=#{description},typeName=#{typeName} where id = #{id}")
    int updateBlog(Blog blog);

    //查询时间排序最新文章
    @Select("select * from blog where recommend=true order by updateTime desc limit 4")
    List<Blog> getRecommendBlog();

    //查询热度排序文章
    @Select("select * from blog  order by views desc limit 3")
    List<Blog> getHotBlog();

    //更新访问量
    @Update("update blog set views=#{views} where id=#{id}")
    void updateViews(Blog blog);

    //查询时间排序最新文章
    @Select("select * from blog  order by updateTime desc")
    List<Blog> getTimeAllBlog();

    @Delete("delete  from blog where id=#{id}")
    void deleteBlog(Long id);

    @Select("select * from blog")
    List<Blog> getAllBlog();

    @Select("select * from blog where typeId=#{typeId}")
    List<Blog> getAllBlogByTypeId(Long typeId);

    @Select("select * from blog where id=#{id}")
    Blog getBlogById(Long id);

    //按文章标题和内容搜索
    @Select("select * from blog where title like CONCAT('%',#{keyword},'%') or description like CONCAT('%',#{keyword},'%')")
    List<Blog> getSearchByTitle(String keyword);



    /**
     * CONCAT：用于字符拼接
     * */
    @Select("select * from blog where title like CONCAT('%',#{title},'%') and typeId=#{typeId}")
    List<Blog> searchBlog(String title,Long typeId);

    @Select("select * from blog where title like CONCAT('%',#{title},'%')")
    List<Blog> SearchByTitle(String title);

    @Select("select * from blog where typeId=#{typeId}")
    List<Blog> SearchByType(Long typeId);

    @Select("select title,recommend,published,updateTime from blog where id=#{id}")
    ShowBlog getShowBlog(Long id);
}
