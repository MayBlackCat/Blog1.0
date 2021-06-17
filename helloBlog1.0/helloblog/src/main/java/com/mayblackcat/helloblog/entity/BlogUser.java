package com.mayblackcat.helloblog.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class BlogUser {
    private Long id;
    private String username;
    private String password;

    @Email
    private String email;
    private String avatar;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private List<Blog> blogs;
}
