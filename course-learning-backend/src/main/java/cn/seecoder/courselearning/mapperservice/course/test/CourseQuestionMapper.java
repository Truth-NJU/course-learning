package cn.seecoder.courselearning.mapperservice.course.test;

import cn.seecoder.courselearning.po.course.test.CourseQuestionPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
import org.springframework.stereotype.Repository;

import java.util.List;



//Unit Test Has Finished
@Repository
public interface CourseQuestionMapper {

    // 教师创建题目，添加到题库
    int createQuestion(CourseQuestionPO courseQuestionPO);

    // 删除题目
    int deleteQuestion(int questionId);

    //根据问题id查询题目
    CourseQuestionPO  selectQuestionByQuestionId(int questionId);

    // 根据测试id查询这次测试的题目
    List<CourseQuestionPO> selectQuestionByTestId(int testId);

    // 根据课程id查询课程的题目
    List<CourseQuestionPO> selectQuestionByCourseId(int courseId);

    // 根据题目id更新题目信息
    int updateQuestion(CourseQuestionPO courseQuestionPO);

    // 添加用户的答案
    int addAnswer(CourseUserTestQuestionPO courseUserTestQuestionPO);

    // 更新用户的答案
    int updateAnswer(CourseUserTestQuestionPO courseUserTestQuestionPO);
}
