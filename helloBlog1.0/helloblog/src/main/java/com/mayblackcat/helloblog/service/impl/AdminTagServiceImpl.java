package com.mayblackcat.helloblog.service.impl;


import com.mayblackcat.helloblog.dao.AdminTagDao;
import com.mayblackcat.helloblog.entity.BlogTag;
import com.mayblackcat.helloblog.exception.NotFoundException;
import com.mayblackcat.helloblog.service.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminTagServiceImpl implements AdminTagService {

    @Autowired
    private AdminTagDao tagDao;

    @Transactional
    public int addTags(BlogTag blogTag) {
        return tagDao.addTags(blogTag);
    }

    @Transactional
    public int modifyTags(BlogTag blogTag) {
        return tagDao.modifyTags(blogTag);
    }


    public List<BlogTag> getListTags() {
        return tagDao.getListTags();
    }

    public BlogTag selectTags(Long id) {
        if (tagDao.selectTags(id)==null){
            throw new NotFoundException("不存在该id，请重新输入");
        }
            return tagDao.selectTags(id);


    }

    //将接收的tag值存放入集合中在数据库中一一查询
    @Transactional
    public List<BlogTag> getTagIds(String stringIds){
        List<BlogTag> blogTagList=new ArrayList<>();
        //接收页面传来的tag.id集合
        //格式为字符串形式，转换成整型
        if (!"".equals(stringIds)&&stringIds!=null){
            String[] split=stringIds.split(",");
            for (int i=0;i<split.length;i++){
                blogTagList.add(tagDao.selectTags(new Long(split[i])));
            }
        }
        return blogTagList;


    }

    @Transactional
    public BlogTag getTagByName(String name){
        return tagDao.getTagByName(name);
    }

    @Transactional
    public Boolean deleteTags(Long id) {
        BlogTag blogTag=tagDao.selectTags(id);

        if (blogTag==null){
            throw new NotFoundException("不存在该id号，无法删除");
        }else {
            return tagDao.deleteTags(id);
        }

    }
}
