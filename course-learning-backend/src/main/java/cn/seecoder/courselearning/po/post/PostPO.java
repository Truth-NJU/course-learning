package cn.seecoder.courselearning.po.post;

import cn.seecoder.courselearning.annotation.NotNull;
import cn.seecoder.courselearning.po.CommonPO;
import cn.seecoder.courselearning.vo.post.PostVO;

import cn.seecoder.courselearning.dto.post.PostDTO;

import java.util.Date;

public class PostPO extends CommonPO {
    //帖子id
    private Integer postId;

    //课程id
    @NotNull
    private Integer courseId;

    //帖子发表用户id
    @NotNull
    private Integer userId;

    //帖子标题
    @NotNull
    private String postTitle;

    //帖子内容
    @NotNull
    private String postContent;

    //帖子发表时间
    @NotNull
    private Date postTime;

    public PostPO() {
    }

    @Override
    public String toString() {
        return "PostPO{" +
                "postId=" + postId +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTime=" + postTime +
                '}';
    }

    public PostPO(int postId, int courseId, int userId, String postTitle, String postContent, Date postTime) {
        this.postId = postId;
        this.courseId = courseId;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postTime = postTime;
    }

    public PostPO(PostDTO postDTO) {
        this.courseId = postDTO.getCourseId();
        this.userId = postDTO.getUserId();
        this.postTitle = postDTO.getTitle();
        this.postContent = postDTO.getContent();
        this.postTime = postDTO.getPostTime();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public PostPO(PostVO postVO) {
        this.postId = postVO.getPostId();
        this.courseId = postVO.getCourseId();
        this.userId = postVO.getUserId();
        this.postTitle = postVO.getPostTitle();
        this.postContent = postVO.getPostContent();
        this.postTime = postVO.getPostTime();
    }


}

