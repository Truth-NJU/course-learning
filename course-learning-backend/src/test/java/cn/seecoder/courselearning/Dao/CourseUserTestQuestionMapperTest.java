package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.mapperservice.course.test.CourseUserTestQuestionMapper;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
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
public class CourseUserTestQuestionMapperTest {
    @Autowired
    CourseUserTestQuestionMapper courseUserTestQuestionMapper;

    @Test
    void finishTestQuestions() {
        CourseUserTestQuestionPO courseUserTestQuestionPO1=new CourseUserTestQuestionPO(1,1,1,"给爷爬",1);
        CourseUserTestQuestionPO courseUserTestQuestionPO2=new CourseUserTestQuestionPO(1,2,1,"给爷",0);
        CourseUserTestQuestionPO courseUserTestQuestionPO3=new CourseUserTestQuestionPO(1,3,1,"给",0);
        CourseUserTestQuestionPO courseUserTestQuestionPO4=new CourseUserTestQuestionPO(1,4,1,"给爷爬",0);
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOS=new ArrayList<>();
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO1);
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO2);
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO3);
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO4);
        courseUserTestQuestionMapper.finishTestQuestions(courseUserTestQuestionPOS);
    }

    @Test
    void updateTestQuestions(){
        CourseUserTestQuestionPO courseUserTestQuestionPO1=new CourseUserTestQuestionPO(1,1,1,"hxtyyds",1);
        CourseUserTestQuestionPO courseUserTestQuestionPO2=new CourseUserTestQuestionPO(1,2,1,"htshd",1);
        CourseUserTestQuestionPO courseUserTestQuestionPO3=new CourseUserTestQuestionPO(1,3,1,"给",0);
       // CourseUserTestQuestionPO courseUserTestQuestionPO4=new CourseUserTestQuestionPO(1,4,1,"给爷爬",0);
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOS=new ArrayList<>();
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO1);
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO2);
        courseUserTestQuestionPOS.add(courseUserTestQuestionPO3);
        //courseUserTestQuestionPOS.add(courseUserTestQuestionPO4);
        courseUserTestQuestionMapper.updateTestQuestions(courseUserTestQuestionPOS);
    }

    @Test
    void selectTestQuestions(){
        CourseUserTestPO courseUserTestPO=new CourseUserTestPO(1,1,0);
        System.out.println(courseUserTestQuestionMapper.selectTestQuestions(courseUserTestPO));
    }
}
