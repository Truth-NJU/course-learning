package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.ReplyPO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;



public class ReplyMapperImpl implements ReplyMapper{
    @Override
    public int reply(ReplyPO replyPO) {
        return 0;
    }

    @Override
    public int updateReply(ReplyPO replyPO) {
        return 0;
    }

    @Override
    public int replyReply(ReplyPO replyPO) {
        return 1;
    }

    @Override
    public List<ReplyPO> selectReplyByRepliedId(int repliedId) {
        return null;
    }

    @Override
    public int updateStatus(int replyId) {
        return 0;
    }

    @Override
    public int deleteReply(int replyId) {
        return 0;
    }

    @Override
    public List<ReplyPO> selectReplyByUserId(int userId) {
        return null;
    }

    @Override
    public List<ReplyPO> selectReplyByPostId(int postId) {
        return null;
    }

    @Override
    public ReplyPO selectReplyByReplyId(int replyId) {
        return null;
    }
}
