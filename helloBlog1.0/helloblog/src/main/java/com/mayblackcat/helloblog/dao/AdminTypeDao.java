package com.mayblackcat.helloblog.dao;

import com.mayblackcat.helloblog.entity.BlogTag;
import com.mayblackcat.helloblog.entity.BlogType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface AdminTypeDao {
    @Insert("insert into blogtype(name) value(#{name})")
    public int addType(BlogType type);

    @Select("select count(*) from blog where typeId=#{typeId}")
    Long findBlogCountByTypeId(Long id);

    @Select("select * from blogtype where id=#{id}")
    public BlogType getType(Long id);

    @Select("select name from blogtype where id=#{id}")
    public String getNameById(Long id);

    @Select("select * from blogtype where name=#{name}")
    public BlogType getTypeByName(String name);

    @Select("select * from blogtype LIMIT 0,6")
    List<BlogType> findLimitType();

    @Update("update blogtype set id=#{id},name=#{name} where id=#{id}")
    public int updateType(BlogType type);

    @Delete("delete from blogtype where id=#{id}")
    public Boolean deleteType(Long id);

    @Select("select * from blogtype")
    public List<BlogType> getListType();
}
