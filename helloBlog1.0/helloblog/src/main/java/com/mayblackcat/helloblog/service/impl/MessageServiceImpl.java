package com.mayblackcat.helloblog.service.impl;

import com.mayblackcat.helloblog.dao.MessageDao;
import com.mayblackcat.helloblog.entity.BlogMessage;
import com.mayblackcat.helloblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageDao messageDao;

    //存放迭代找出的所有子代的集合
    private List<BlogMessage> tempReplys = new ArrayList<>();

    /**
     * @Description: 查询留言
     * @Param:
     * @Return: 留言消息
     */

    public List<BlogMessage> listMessage() {
        //查询出父节点
        List<BlogMessage> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        for (BlogMessage message : messages) {
            Long id = message.getId();
            String parentNickname1 = message.getNickName();
            List<BlogMessage> childMessages = messageDao.findByParentIdNotNull(id);
            //查询出子留言
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    /**
     * @Description: 查询出子留言
     * @Param: childMessages：所有子留言
     * @Param: parentNickname1：父留言的姓名
     * @Return:
     */
    private void combineChildren(List<BlogMessage> childMessages, String parentNickname1) {
        //判断是否有一级子回复
        if (childMessages.size() > 0) {
            //循环找出子留言的id
            for (BlogMessage childMessage : childMessages) {
                String parentNickname = childMessage.getNickName();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Param: childId：子留言的id
     * @Param: parentNickname1：子留言的姓名
     * @Return:
     */
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级留言的id找到子二级留言
        List<BlogMessage> replayMessages = messageDao.findByReplayId(childId);

        if (replayMessages.size() > 0) {
            for (BlogMessage replayMessage : replayMessages) {
                String parentNickname = replayMessage.getNickName();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                //循环迭代找出子集回复
                recursively(replayId, parentNickname);
            }
        }
    }


    //存储留言信息
    @Transactional
    public int saveMessage(BlogMessage message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    //    删除留言
    @Transactional
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }
}
