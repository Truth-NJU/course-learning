package cn.seecoder.courselearning.po.post;

import cn.seecoder.courselearning.annotation.NotNull;
import cn.seecoder.courselearning.po.CommonPO;
import cn.seecoder.courselearning.vo.post.ReplyVO;

import cn.seecoder.courselearning.dto.post.ReplyDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//对应reply表
@Setter
@Getter
public class ReplyPO extends CommonPO {
    //回复id
    Integer replyId;

    //回复的帖子id
    //如果是回复回复，则是null
    Integer postId;

    //发表回复的用户id
    @NotNull
    Integer replyUserId;

    //被回复的用户id
    @NotNull
    Integer repliedUserId;

    //回复内容
    @NotNull
    String replyContent;

    //被回复的评论的id
    //如果是回复帖子，则是null
    Integer repliedId;

    //回复时间
    @NotNull
    Date replyTime;

    //该回复是否被用户查看过
    // 0代表没有被查看 1代表查看过了
    @NotNull(type = NotNull.Type.INT)
    Integer status;

    public ReplyPO() {
    }

    public ReplyPO(int replyId, int postId, int replyUserId, int repliedUserId, String replyContent, int repliedId, Date replyTime, int status) {
        this.replyId = replyId;
        this.postId = postId;
        this.replyUserId = replyUserId;
        this.repliedUserId = repliedUserId;
        this.replyContent = replyContent;
        this.repliedId = repliedId;
        this.replyTime = replyTime;
        this.status = status;
    }

    public ReplyPO(ReplyDTO replyDTO) {
        replyId = replyDTO.getReplyId();
        postId = replyDTO.getPostId();
        replyUserId = replyDTO.getReplyUserId();
        repliedId = replyDTO.getRepliedId();
        repliedUserId = replyDTO.getRepliedUserId();
        replyContent = replyDTO.getReplyContent();
        replyTime = replyDTO.getReplyTime();
        status = replyDTO.getStatus();
    }

    @Override
    public String toString() {
        return "ReplyPO{" +
                "replyId=" + replyId +
                ", postId=" + postId +
                ", replyUserId=" + replyUserId +
                ", repliedUserId=" + repliedUserId +
                ", replyContent='" + replyContent + '\'' +
                ", repliedId=" + repliedId +
                ", replyTime=" + replyTime +
                ", status=" + status +
                '}';
    }


    public boolean isLooked() {
        return status == 1;
    }

}
