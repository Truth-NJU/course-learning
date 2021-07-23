package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.mapperservice.course.test.CourseUserTestMapper;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseUserTestMapperTest {
    @Autowired
    CourseUserTestMapper courseUserTestMapper;

    @Test
    void finishTest(){
        CourseUserTestPO courseUserTestPO=new CourseUserTestPO(1,5,0);
        courseUserTestMapper.finishTest(courseUserTestPO);
    }

    @Test
    void updateTest(){
        CourseUserTestPO courseUserTestPO=new CourseUserTestPO(1,5,100);
        courseUserTestMapper.updateTest(courseUserTestPO);
    }
}
