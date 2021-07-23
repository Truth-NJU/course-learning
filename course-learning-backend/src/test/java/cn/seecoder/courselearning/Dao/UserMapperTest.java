package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.mapperservice.post.PostMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.post.PostPO;
import cn.seecoder.courselearning.po.user.User;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void insert() {
        User user=new User(0,"丁笑宇","19850355091","123456",null,0, UserRole.TEACHER,new Date(),100,new Date());
        assert 1==userMapper.insert(user);
        assert 5==user.getId();
    }

    @Test
    void deleteByPriamryKey(){
        assert 1==userMapper.deleteByPrimaryKey(3);

    }

    @Test
    void updateUserInfo(){
        User user=new User(2,"丁笑宇","19850355091","123456",null,0, UserRole.TEACHER,new Date(),1,new Date());
        assert 1==userMapper.updateByPrimaryKey(user);
    }
}