package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.enums.QuestionType;
import cn.seecoder.courselearning.mapperservice.course.test.CourseQuestionMapper;
import cn.seecoder.courselearning.po.course.test.CourseQuestionPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseQuestionMapperTest {

    @Autowired
    CourseQuestionMapper courseQuestionMapper;

    @Test
    void createQuestion() {
        CourseQuestionPO courseQuestionPO=new CourseQuestionPO(0,"dxy是是傻逼吗？","是","因为所以他很傻逼",1, QuestionType.choice);
        assert 1== courseQuestionMapper.createQuestion(courseQuestionPO);
    }

    @Test
    void updateQuestionTest(){
        CourseQuestionPO courseQuestionPO=new CourseQuestionPO(5,"答案是什么？","不是","因为所以他很傻逼",1,QuestionType.choice);
        assert 1==courseQuestionMapper.updateQuestion(courseQuestionPO);
    }

    @Test
    void selectQuestionByCourseId(){
        List<CourseQuestionPO> courseQuestionPOS=courseQuestionMapper.selectQuestionByCourseId(1);
        System.out.println(courseQuestionPOS.get(0).toString());
        assert 1==courseQuestionPOS.size();
    }

    @Test
    void deleteQuestion(){
        assert 1==courseQuestionMapper.deleteQuestion(1);
    }

    @Test
    void selectQuestionByTestId(){
        List<CourseQuestionPO> courseQuestionPOS=courseQuestionMapper.selectQuestionByTestId(1);
        System.out.println(courseQuestionPOS.get(0).toString());
        assert 1==courseQuestionPOS.size();

    }
    @Test
    void testAll(){
        CourseQuestionPO courseQuestionPO=new CourseQuestionPO(0,"dxy是是傻逼吗？","是","因为所以他很傻逼",1,QuestionType.choice);
        assert 1==courseQuestionMapper.createQuestion(courseQuestionPO);
        assert 1==courseQuestionMapper.createQuestion(courseQuestionPO);
        List<CourseQuestionPO> courseQuestionPOS=courseQuestionMapper.selectQuestionByCourseId(1);
        assert 2==courseQuestionPOS.size();
        assert 1==courseQuestionMapper.deleteQuestion(3);
        assert 1==courseQuestionMapper.deleteQuestion(4);

    }

    @Test
    void addAnswer(){
        CourseUserTestQuestionPO courseUserTestQuestionPO=new CourseUserTestQuestionPO(1,1,1,"给爷爬",0);

        assert 1==courseQuestionMapper.addAnswer(courseUserTestQuestionPO);
    }

    @Test
    void updateAnswer(){
        CourseUserTestQuestionPO courseUserTestQuestionPO=new CourseUserTestQuestionPO(1,1,1,"给爬",1);

        assert 1==courseQuestionMapper.updateAnswer(courseUserTestQuestionPO);
    }

    @Test
    void selectQuestionByQuestionId(){
        CourseQuestionPO courseQuestionPO=courseQuestionMapper.selectQuestionByQuestionId(2);
        assert "s d j ka".equals(courseQuestionPO.getQuestion());
    }
}
