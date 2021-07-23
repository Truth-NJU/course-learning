package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.ReplyPO;
import cn.seecoder.courselearning.vo.post.ReplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;


//Unit Test Has Finished
@Repository("PostReplyMapper")
public interface ReplyMapper {

    //回复帖子
    int reply(ReplyPO replyPO);

    //更新回复内容和时间
    int updateReply(ReplyPO replyPO);

    //回复评论
    int replyReply(ReplyPO replyPO);

    //根据repliedId找reply
    List<ReplyPO> selectReplyByRepliedId(int repliedId);

    //更新回复是否已经被查看过的状态
    int updateStatus(int replyId);

    //删除回复
    int deleteReply(int replyId);

    // 根据评论发出者的userid找评论
    List<ReplyPO> selectReplyByUserId(int userId);


    //根据postID查询回复并按时间从大到小输出
    List<ReplyPO> selectReplyByPostId(int postId);

    //根据主键查询reply
    ReplyPO selectReplyByReplyId(int replyId);

}
