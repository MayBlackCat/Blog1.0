package com.mayblackcat.helloblog.service.impl;

import com.mayblackcat.helloblog.dao.AdminDao;
import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.service.AdminService;
import com.mayblackcat.helloblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//将Service类注册到bean中
@Service
public class AdminServiceImpl implements AdminService {

    //自动装配
    @Autowired
    private AdminDao adminDao;


    @Transactional
    public BlogUser adminService(String username, String password) {
        //数据库查询用户名和密码
        BlogUser user=adminDao.findByUser(username, MD5Utils.code(password));
        return user;
    }

    @Transactional
    public void updatePassword(BlogUser blogUser){

        adminDao.updatePassword(blogUser);
    }

    public BlogUser getAvatarById(Long userId){
        return adminDao.getAvatarById(userId);
    }
}
