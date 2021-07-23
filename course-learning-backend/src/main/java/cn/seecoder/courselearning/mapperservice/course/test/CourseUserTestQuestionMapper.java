package cn.seecoder.courselearning.mapperservice.course.test;

import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Unit Test Has Finished
@Repository
public interface CourseUserTestQuestionMapper {
    // 记录用户的答案
    void finishTestQuestions(List<CourseUserTestQuestionPO> courseUserTestQuestionPOS);

    // 更新用户答案
    void updateTestQuestions( List<CourseUserTestQuestionPO> courseUserTestQuestionPOS);

    //查询用户的答案
    List<CourseUserTestQuestionPO> selectTestQuestions(CourseUserTestPO courseUserTestPO);
}
