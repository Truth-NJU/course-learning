package cn.seecoder.courselearning.mapperservice.course.test;

import cn.seecoder.courselearning.dto.course.CourseTestQuestionAddDTO;
import cn.seecoder.courselearning.po.course.test.CourseTestQuestionPO;
import org.springframework.stereotype.Repository;

import java.util.List;

//Unit Test Has Finished
@Repository
public interface CourseTestQuestionMapper {
    // 选择题目组成测试
    void addTestQuestion(List<CourseTestQuestionPO> courseTestQuestionPOS);

    // 删除测试里的题目
    int deleteTestQuestion(int testId, int questionId);

    void deleteAllTestQuestion(Integer testId);
}
