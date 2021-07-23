package cn.seecoder.courselearning.mapperservice.course.test.mapperStub;

import cn.seecoder.courselearning.enums.QuestionType;
import cn.seecoder.courselearning.enums.TestStatus;
import cn.seecoder.courselearning.mapperservice.course.test.CourseTestMapper;
import cn.seecoder.courselearning.po.course.test.CourseQuestionPO;
import cn.seecoder.courselearning.po.course.test.CourseTestPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CourseTestMapperImplStub implements CourseTestMapper {
    @Override
    public int createTest(CourseTestPO courseTestPO) {
        courseTestPO.setTestId(1);
        return 0;
    }

    @Override
    public int deleteTest(int testId) {
        return 0;
    }

    @Override
    public int updateTest(CourseTestPO courseTestPO) {
        return 0;
    }

    @Override
    public List<CourseTestPO> findAllTest(int courseId) {
        List<CourseTestPO> courseTestPOList = new ArrayList<>();
        courseTestPOList.add(new CourseTestPO(1, new Date(System.currentTimeMillis() - 1000), new Date(System.currentTimeMillis() + 1000), 1, "123", TestStatus.toStart));
        courseTestPOList.add(new CourseTestPO(2, new Date(System.currentTimeMillis() - 1000), new Date(System.currentTimeMillis() + 1000), 1, "123", TestStatus.Running));
        courseTestPOList.add(new CourseTestPO(3, new Date(System.currentTimeMillis() - 1000), new Date(System.currentTimeMillis() + 1000), 1, "123", TestStatus.Finish));
        courseTestPOList.add(new CourseTestPO(4, new Date(System.currentTimeMillis() + 1111000), new Date(System.currentTimeMillis() + 11111000), 1, "123", TestStatus.toStart));
        return courseTestPOList;
    }

    @Override
    public CourseTestPO selectTestById(int testId) {
        return null;
    }

    @Override
    public int addQuestion(int testId, int questionId) {
        return 0;
    }

    @Override
    public int updateScore(CourseUserTestPO courseUserTestScorePO) {
        return 0;
    }

    @Override
    public int addTestParticipation(CourseUserTestPO courseUserTestScorePO) {
        return 0;
    }

    @Override
    public CourseUserTestPO findMyScore(CourseUserTestPO courseUserTestScorePO) {
        CourseUserTestPO courseUserTestPO_I_DONE = new CourseUserTestPO(1, 1, new Random().nextInt() % 100);

        CourseUserTestPO courseUserTestPO_NOT_DONE = null;

        return courseUserTestPO_I_DONE;
    }
}
