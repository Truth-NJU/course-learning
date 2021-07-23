package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.mapperservice.post.ReplyMapper;
import cn.seecoder.courselearning.po.post.ReplyPO;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
public class ReplyMapperTest {

    @Autowired
    ReplyMapper replyMapper;

    @Test
    public void reply(){
        ReplyPO replyPO =new ReplyPO(100,2,2,1,"文化好我发化",1,new Date(),0);
        assert 1== replyMapper.reply(replyPO);
        assert replyPO.getReplyId()==9;
    }

    @Test
    public void updateReply(){
        ReplyPO replyPO =new ReplyPO(1,2,2,1,"哈哈哈哈哈啊",0,new Date(),1);
        assert 1== replyMapper.updateReply(replyPO);
    }

    @Test
    public void updateStatus(){
        assert 1== replyMapper.updateStatus(1);
    }

    @Test
    public void deleteReply(){
        assert 1== replyMapper.deleteReply(2);
    }

    @Test
    public void selectReplyByPostId(){
        List<ReplyPO> replyPOS = replyMapper.selectReplyByPostId(2);
        for(int i = 0; i< replyPOS.size(); i++){
            System.out.println(replyPOS.get(i).toString());
        }
    }


    @Test
    public void selectReplyByReplyId(){
        List<ReplyPO> replyPO = replyMapper.selectReplyByRepliedId(1);
        assert replyPO.size()==2;

    }

    @Test
    public void selectReplyByUserId(){
        List<ReplyPO> replyPOS = replyMapper.selectReplyByUserId(2);
        for(int i = 0; i< replyPOS.size(); i++){
            System.out.println(replyPOS.get(i).toString());
        }
    }
}

