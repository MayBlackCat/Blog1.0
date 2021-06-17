package com.mayblackcat.helloblog.dao;

import com.mayblackcat.helloblog.entity.BlogMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {


        @Insert("insert into blogmessage(id,nickName,email,content,avatar,createTime,parentMessageId,adminMessage) " +
                "values(#{id},#{nickName},#{email},#{content},#{avatar},#{createTime},#{parentMessageId},#{adminMessage})")
        //添加一个评论
        int saveMessage(BlogMessage message);

        @Select("select * from blogmessage where parentMessageId = #{parentMessageId} order by createTime desc")
        //查询父级评论
        List<BlogMessage> findByParentIdNull(@Param("parentMessageId") Long ParentId);

        //查询一级回复
        @Select(" select * from blogmessage  where parentMessageId = #{id}  order by createTime desc")
        List<BlogMessage> findByParentIdNotNull(@Param("id") Long id);

        //查询二级以及所有子集回复
        @Select("select *  from blogmessage where parentMessageId = #{sonMessageId} order by createTime desc")
        List<BlogMessage> findByReplayId(@Param("sonMessageId") Long childId);

        //删除评论
        @Delete("delete from blogmessage where id = #{id}")
        void deleteMessage(Long id);


}
