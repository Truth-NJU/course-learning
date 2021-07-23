package cn.seecoder.courselearning.vo.post;

import cn.seecoder.courselearning.po.post.ReplyPO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReplyVO {

    //回复id
    private Integer replyId;

    //回复的帖子id
    private Integer postId;

    //发表回复的用户id
    private Integer replyUserId;

    //被回复的用户id
    private Integer repliedUserId;

    //回复内容
    private String replyContent;

    //被回复的评论的id
    //如果是回复帖子，则是0。
    private Integer repliedId;

    //回复时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyTime;

    //该回复是否被用户查看过
    // 0代表没有被查看 1代表查看过了
    private Integer status;

    //回复的新回复数字
    private Integer newReplyNum;

    //回复的新回复ID
    private List<Integer> newReplyList;

    public ReplyVO() {
    }

    public ReplyVO(int replyId, int postId, int replyUserId, int repliedUserId, String replyContent, int repliedId, Date replyTime, int status) {
        this.replyId = replyId;
        this.postId = postId;
        this.replyUserId = replyUserId;
        this.repliedUserId = repliedUserId;
        this.replyContent = replyContent;
        this.repliedId = repliedId;
        this.replyTime = replyTime;
        this.status = status;
    }

    public ReplyVO(ReplyPO replyPO) {
        this.replyId = replyPO.getReplyId();
        this.postId = replyPO.getPostId();
        this.replyUserId =replyPO.getReplyUserId();
        this.repliedUserId = replyPO.getRepliedUserId();
        this.replyContent = replyPO.getReplyContent();
        this.repliedId =replyPO.getRepliedId();
        this.replyTime = replyPO.getReplyTime();
        this.status = replyPO.getStatus();
    }

}
