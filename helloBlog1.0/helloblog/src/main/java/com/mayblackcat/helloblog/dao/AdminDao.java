package com.mayblackcat.helloblog.dao;

import com.mayblackcat.helloblog.entity.BlogUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * @NoRepositoryBean
 * 注释将存储库接口排除在外，从而导致创建实例。
 *
 * 当为所有存储库提供扩展基接口时，通常会使用该接口，并结合自定义存储库基类来实现在该中间接口中声明的方法。在这种情况下，您通常从中间接口派生出具体的存储库接口，但不想为中间接口创建springbean。
 */
//将DAO接口注册到bean中
@Repository
public interface AdminDao {
    @Select("select * from bloguser where username=#{username} and password=#{password}")
    public BlogUser findByUser(String username, String password);

    @Update("update bloguser set password=#{password} where username=#{username}")
    public void updatePassword(BlogUser blogUser);

    @Select("select * from bloguser where id=#{id}")
    public BlogUser getAvatarById(Long userId);


}
