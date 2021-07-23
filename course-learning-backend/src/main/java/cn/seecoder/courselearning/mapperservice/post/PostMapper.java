package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.PostPO;
import org.springframework.stereotype.Repository;

import java.util.List;

//Unit Test Has Finished
@Repository("PostMapper")
public interface PostMapper {

    //发布帖子
    int addPost(PostPO postPO);

    //更新帖子内容
    int updatePost(PostPO postPO);

    //删除帖子
    int deletePost(int postId);

    //根据课程id查看讨论区帖子内容
    List<PostPO> selectPostByCourseId(int courseId);

    //根据用户id查看帖子
    List<PostPO> selectPostByUserId(int userId);

    //根据帖子id查看帖子内容
    PostPO selectPostByPostId(int postId);


}
