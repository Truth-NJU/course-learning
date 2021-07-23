package cn.seecoder.courselearning.mapperservice.post;


import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.post.PostPO;
import org.junit.Before;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PostMapperImpl implements PostMapper {

        PostPO postPO1 = new PostPO(1, 1, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO2 = new PostPO(2, 1, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO3 = new PostPO(3, 1, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO4 = new PostPO(4, 1, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO5 = new PostPO(5, 2, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO6 = new PostPO(6, 2, 1, "哈哈哈哈哈哈", "defew", new Date());
        PostPO postPO7 = new PostPO(7, 2, 1, "哈哈哈哈哈哈", "defew", new Date());


    @Override
    public int addPost(PostPO postPO) {
        return 1;
    }

    @Override
    public int updatePost(PostPO postPO) {
        return 0;
    }

    @Override
    public int deletePost(int postId) {
        return 0;
    }

    @Override
    public List<PostPO> selectPostByCourseId(int courseId) {
        List<PostPO> postPOS=new ArrayList<>();
        postPOS.add(postPO1);
        postPOS.add(postPO2);
        postPOS.add(postPO3);
        postPOS.add(postPO4);
        return postPOS;
    }

    @Override
    public List<PostPO> selectPostByUserId(int userId) {
        return null;
    }

    @Override
    public PostPO selectPostByPostId(int postId) {
        return null;
    }
}
