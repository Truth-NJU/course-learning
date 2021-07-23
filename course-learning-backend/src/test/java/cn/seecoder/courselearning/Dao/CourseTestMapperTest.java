package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.enums.TestStatus;
import cn.seecoder.courselearning.mapperservice.course.test.CourseTestMapper;
import cn.seecoder.courselearning.po.course.test.CourseTestPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
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
public class CourseTestMapperTest {

    @Autowired
    CourseTestMapper courseTestMapper;


    @Test
    void createTest() {
        CourseTestPO courseTestPO = new CourseTestPO(0, new Date(), new Date(), 1, "课堂测验", TestStatus.toStart);
        assert 1 == courseTestMapper.createTest(courseTestPO);
        assert courseTestPO.getTestId()==14;
    }

    @Test
    void addQuestion() {
        assert 1 == courseTestMapper.addQuestion(1, 3);
    }

    @Test
    void updateTest() {
        CourseTestPO courseTestPO = new CourseTestPO(9, new Date(), new Date(), 1, "课堂课堂", TestStatus.toStart);
        assert 1 == courseTestMapper.updateTest(courseTestPO);
    }

    @Test
    void findAllTest() {
        List<CourseTestPO> courseTestPOS = courseTestMapper.findAllTest(1);
        System.out.println(courseTestPOS);
        assert 1 == courseTestPOS.size();
}

    @Test
    void deleteTest() {
        assert 1 == courseTestMapper.deleteTest(8);
    }

    @Test
    void testAll() {
        CourseTestPO courseTestPO = new CourseTestPO(0, new Date(), new Date(), 1, "期中考试", TestStatus.toStart);
        assert 1 == courseTestMapper.createTest(courseTestPO);
        assert 1 == courseTestMapper.createTest(courseTestPO);
        List<CourseTestPO> courseTestPOS = courseTestMapper.findAllTest(1);
        assert 2 == courseTestPOS.size();
        assert 1 == courseTestMapper.deleteTest(4);
        assert 1 == courseTestMapper.deleteTest(5);
    }

    @Test
    void updateScore() {
        CourseUserTestPO courseUserTestPO = new CourseUserTestPO(1, 1, 100);
        assert 1 == courseTestMapper.updateScore(courseUserTestPO);
    }

    @Test
    void addTestParticipation() {
        CourseUserTestPO courseUserTestPO = new CourseUserTestPO(1, 4, 0);
        assert 1 == courseTestMapper.addTestParticipation(courseUserTestPO);
    }

    @Test
    void findMyScore()
    {
        CourseUserTestPO courseUserTestPO=new CourseUserTestPO(1,1,0);
        assert 33==courseTestMapper.findMyScore(courseUserTestPO).getScore();
        CourseUserTestPO courseUserTestPO1=new CourseUserTestPO(3,3,3);
        assert null==courseTestMapper.findMyScore(courseUserTestPO1);
    }}
