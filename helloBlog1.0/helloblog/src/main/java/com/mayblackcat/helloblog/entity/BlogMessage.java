package com.mayblackcat.helloblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@ToString
public class BlogMessage {


        private Long id;
        private String nickName;
        private String email;
        private String content;
        private String avatar;
        private Date createTime;
        private Long parentMessageId;
        private boolean adminMessage;

        //回复评论
        private List<BlogMessage> replyMessages;
        private BlogMessage parentMessage;
        private String parentNickname;


}
