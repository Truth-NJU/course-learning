package cn.seecoder.courselearning.vo.post;

import cn.seecoder.courselearning.po.post.PostPO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostVO {
    //帖子id
    private Integer postId;

    //课程id
    private Integer courseId;

    //帖子发表用户id
    private Integer userId;

    //帖子标题
    private String postTitle;

    //帖子内容
    private String postContent;

    //帖子发表时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;

    //帖子的新回复数字
    private Integer newReplyNum;

    //帖子的新回复ID
    private List<Integer> newReplyList;

    public PostVO() {
    }

    public PostVO(int postId, int courseId, int userId, String postTitle, String postContent, Date postTime) {
        this.postId = postId;
        this.courseId = courseId;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postTime = postTime;
    }

    public PostVO(PostPO postPO) {
        this.postId = postPO.getPostId();
        this.courseId =postPO.getCourseId();
        this.userId =postPO.getUserId();
        this.postTitle = postPO.getPostTitle();
        this.postContent = postPO.getPostContent();
        this.postTime = postPO.getPostTime();
    }
}
