package cn.seecoder.courselearning.mapperservice.course.test;

import cn.seecoder.courselearning.po.course.test.CourseTestPO;
import cn.seecoder.courselearning.po.course.test.CourseTestQuestionPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//Unit Test Has Finished
@Repository
public interface CourseTestMapper {
    // 教师创建test
    int createTest(CourseTestPO courseTestPO);

    //根据测试id删除测试
    int deleteTest(int testId);

    // 根据测试id更新测试信息
    int updateTest(CourseTestPO courseTestPO);

    // 根据课程id获得课程的测试列表
    List<CourseTestPO>  findAllTest(int courseId);

    // 根据id获取测试
    CourseTestPO selectTestById(int testId);

    // 为测试选择题目
    int addQuestion(int testId, int questionId);

    // 更新用户测试的分数
    int updateScore(CourseUserTestPO courseUserTestScorePO);

    // 参与测试则将用户id和测试id加入user_test表
    int addTestParticipation(CourseUserTestPO courseUserTestPO);

    // 用户查询分数
    CourseUserTestPO findMyScore(CourseUserTestPO courseUserTestScorePO);
}
