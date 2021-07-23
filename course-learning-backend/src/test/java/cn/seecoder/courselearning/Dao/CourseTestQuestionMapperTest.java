package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.mapperservice.course.test.CourseTestQuestionMapper;
import cn.seecoder.courselearning.po.course.test.CourseTestQuestionPO;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseTestQuestionMapperTest {
    @Autowired
    CourseTestQuestionMapper courseTestQuestionMapper;

    @Test
    void addTestQuestion(){
        CourseTestQuestionPO courseTestQuestionPO1=new CourseTestQuestionPO(1,1);
        CourseTestQuestionPO courseTestQuestionPO2=new CourseTestQuestionPO(1,2);
        CourseTestQuestionPO courseTestQuestionPO3=new CourseTestQuestionPO(1,3);
        CourseTestQuestionPO courseTestQuestionPO4=new CourseTestQuestionPO(1,4);
        List<CourseTestQuestionPO> courseTestQuestionPOS=new ArrayList<>();
        courseTestQuestionPOS.add(courseTestQuestionPO1);
        courseTestQuestionPOS.add(courseTestQuestionPO2);
        courseTestQuestionPOS.add(courseTestQuestionPO3);
        courseTestQuestionPOS.add(courseTestQuestionPO4);

        courseTestQuestionMapper.addTestQuestion(courseTestQuestionPOS);
    }

    @Test
    void deleteTestQuestion(){
        assert 1==courseTestQuestionMapper.deleteTestQuestion(1,3);
    }
}
