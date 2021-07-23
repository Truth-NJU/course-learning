package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.mapperservice.post.PostMapper;
import cn.seecoder.courselearning.po.post.PostPO;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void addPost() {
        PostPO postPO=new PostPO(1,1,1,"哈哈哈哈哈哈","defew",new Date());
        assert 1==postMapper.addPost(postPO);
        assert postPO.getPostId()==6;
    }

    @Test
    void updatePost(){
        PostPO postPO=new PostPO(1,1,1,"为什么软工","dwfee",new Date());
        assert 1==postMapper.updatePost(postPO);
    }

    @Test
    void deletePost(){
        assert 1==postMapper.deletePost(1);
    }

    @Test
    void selectPostByCourseId(){
        List<PostPO> postPOS=postMapper.selectPostByCourseId(1);
        for(int i=0;i<postPOS.size();i++){
            System.out.println(postPOS.get(i).toString());
        }
    }

    @Test
    void selectPostByPostId(){
        PostPO postPO=postMapper.selectPostByPostId(2);
        System.out.println(postPO.toString());

    }

    @Test
    void selectPostByUserId(){
        List<PostPO> postPOS=postMapper.selectPostByUserId(1);
        for(int i=0;i<postPOS.size();i++){
            System.out.println(postPOS.get(i).toString());
        }
    }
}
