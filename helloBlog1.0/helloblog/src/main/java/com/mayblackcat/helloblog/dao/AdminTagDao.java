package com.mayblackcat.helloblog.dao;

import com.mayblackcat.helloblog.entity.BlogTag;
import com.mayblackcat.helloblog.entity.BlogType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public interface AdminTagDao {

    @Insert("insert into blogtag(id,name) value(#{id},#{name})")
    public int addTags(BlogTag blogTag);

    @Delete("delete from blogtag where id=#{id}")
    public Boolean deleteTags(Long id);

    @Select("select * from blogtag where id=#{id}")
    public BlogTag selectTags(Long id);



    @Update("update blogtag set name=#{name} where id=#{id}")
    public int modifyTags(BlogTag tag);

    @Select("select * from blogtag")
    public List<BlogTag> getListTags();

    @Select("select * from blogTag where name=#{name}")
    public BlogTag getTagByName(String name);

}
