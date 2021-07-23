package cn.seecoder.courselearning.service.course;

import cn.seecoder.courselearning.dto.post.PostDTO;
import cn.seecoder.courselearning.dto.post.ReplyDTO;
import cn.seecoder.courselearning.po.post.PostPO;
import cn.seecoder.courselearning.po.post.ReplyPO;
import cn.seecoder.courselearning.service.post.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursePostServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void CreatePost(){
        PostDTO postDTO=new PostDTO(1,1,1,"三点几了",new Date(),"做作乱啊做做做");
        assert "操作成功"==postService.createPost(postDTO).getMsg();
    }

    @Test
    public void deletePost(){
        PostDTO postDTO=new PostDTO(1,1,1,"三点几了",new Date(),"做作乱啊做做做");
        assert "操作失败"==postService.deletePost(postDTO).getMsg();
    }

    @Test
    public void createReply(){
        ReplyDTO replyDTO=new ReplyDTO(1,1,1,1,"三点几了",1,new Date(),0);
        assert "操作失败"==postService.createReply(replyDTO).getMsg();
    }

    @Test
    public void createReply2Reply(){
        ReplyDTO replyDTO=new ReplyDTO(1,1,1,1,"三点几了",1,new Date(),0);
        assert "操作成功"==postService.createReply2Reply(replyDTO).getMsg();
    }

    @Test
    public void searchPosts(){
        System.out.println(postService.searchPosts(1,2,4));
    }

    @Test
    public void searchReplies(){
        System.out.println(postService.searchReplies(2,2,5,5));
    }

    @Test
    public void searchReply2Reply(){
        System.out.println(postService.searchReply2Reply(1,2,2,5));
    }

    @Test
    public void searchUserPosts(){
        System.out.println(postService.searchUserPosts(2,1,4));
    }

    @Test
    public void searchUserReplies(){
        System.out.println(postService.searchUserReplies(2,1,4));
    }
}
