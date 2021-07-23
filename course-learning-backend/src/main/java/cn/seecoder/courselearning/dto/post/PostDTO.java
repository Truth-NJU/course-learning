package cn.seecoder.courselearning.dto.post;

import lombok.Getter;

import java.util.Date;

@Getter
public class PostDTO {
    private  int postId;

    private  int userId;

    private int courseId;

    private String title;

    private Date postTime;

    private  String content;

    public PostDTO() {
    }

    public PostDTO(int postId, int userId, int courseId, String title, Date postTime, String content) {
        this.postId = postId;
        this.userId = userId;
        this.courseId = courseId;
        this.title = title;
        this.postTime = postTime;
        this.content = content;
    }
}
