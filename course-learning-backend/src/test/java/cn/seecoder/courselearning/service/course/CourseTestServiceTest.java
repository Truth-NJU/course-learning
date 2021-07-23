package cn.seecoder.courselearning.service.course;


import cn.seecoder.courselearning.dto.course.CourseQuestionDTO;
import cn.seecoder.courselearning.dto.course.CourseTestDTO;
import cn.seecoder.courselearning.dto.course.CourseTestFinishDTO;
import cn.seecoder.courselearning.enums.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTestServiceTest {
    @Autowired
    CourseTestService courseTestService;

    @Test
    public void createQuestion() {
        System.out.println(courseTestService.createQuestion(
                new CourseQuestionDTO(1, "dxydsb", null
                        , "2333", "6666", QuestionType.ask)));
    }

    @Test
    public void getQuestionsByCourseId() {
        System.out.println(courseTestService.getQuestionsByCourseId(0));
    }

    @Test
    public void getQuestionsByTestId_Student() {
        System.out.println(courseTestService.getQuestionsByTestId(1, 1));
    }

    @Test
    public void createTest() {
        System.out.println(courseTestService.createTest(new CourseTestDTO(1, null, "123", null, new Date(System.currentTimeMillis() - 1000),
                new Date(System.currentTimeMillis() + 24 * 3600 * 1000))));
        System.out.println(courseTestService.createTest(new CourseTestDTO(1, null, "123", null, new Date(System.currentTimeMillis() + 10000),
                new Date(System.currentTimeMillis() + 24 * 3600 * 1000))));
        System.out.println(courseTestService.createTest(new CourseTestDTO(1, null, "123", null, new Date(System.currentTimeMillis() - 100000000),
                new Date(System.currentTimeMillis() - 24 * 3600 * 1000))));
    }

    @Test
    public void getTestsByCourseId_Teacher() {
        System.out.println(courseTestService.getTestsByCourseId(0));
    }

    @Test
    public void getTestsByCourseId_Student() {
        System.out.println(courseTestService.getTestsByCourseId(0,1));
    }

    @Test
    public void getTestsByTeacherId() {
        System.out.println(courseTestService.getTestsByCourseId(0));
}

    @Test
    public void finishTest(){
        Map<Integer, String> answers =new HashMap<>();
        answers.put(1, "Yes");
        answers.put(2, "233");
        answers.put(3, "1");
        System.out.println(courseTestService.finishTest(new CourseTestFinishDTO(1,1,answers)));
    }


}