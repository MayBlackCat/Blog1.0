package com.mayblackcat.helloblog.service.impl;

import com.mayblackcat.helloblog.dao.AdminTypeDao;
import com.mayblackcat.helloblog.exception.NotFoundException;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.service.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdminTypeServiceImpl implements AdminTagService {
    @Autowired
    private AdminTypeDao typeDao;

    @Transactional
    public int Type(BlogType type)  {
       return typeDao.addType(type);

    }

    @Transactional
    public BlogType getType(Long id){

        return typeDao.getType(id);
    }

    public List<BlogType> findLimitType(){
        return  typeDao.findLimitType();
    }


    public List<BlogType> findAllType(List<BlogType> types) {
        for (BlogType type : types) {
            //得出每种类型的博客数量
            type.setBlogCount(typeDao.findBlogCountByTypeId(type.getId()));
        }
        return types;
    }

    @Transactional
    public BlogType getTypeName(String name){
        return typeDao.getTypeByName(name);
    }

    public String getNameById(Long id){
        return typeDao.getNameById(id);
    }

    @Transactional
    public List<BlogType> getListType(){
        List<BlogType> types =  typeDao.getListType();
        return types;
    }

    @Transactional
    public Boolean deleteType(Long id){
        return typeDao.deleteType(id);
    }

    @Transactional
    public int updateType(BlogType type){
        return typeDao.updateType(type);
    }
}
