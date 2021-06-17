package com.mayblackcat.helloblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlogType {
    private Long id;
    private String name;    //分类名
    private Long blogCount;

    private List<Blog> blogs;
}
