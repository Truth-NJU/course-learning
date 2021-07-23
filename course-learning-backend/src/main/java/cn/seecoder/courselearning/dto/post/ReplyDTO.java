package cn.seecoder.courselearning.dto.post;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
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
    private Date replyTime;

    //该回复是否被用户查看过
    // 0代表没有被查看 1代表查看过了
    private Integer status;

    public ReplyDTO() {
    }

    public ReplyDTO(int replyId, int postId, int replyUserId, int repliedUserId, String replyContent, int repliedId, Date replyTime, int status) {
        this.replyId = replyId;
        this.postId = postId;
        this.replyUserId = replyUserId;
        this.repliedUserId = repliedUserId;
        this.replyContent = replyContent;
        this.repliedId = repliedId;
        this.replyTime = replyTime;
        this.status = status;
    }
}
