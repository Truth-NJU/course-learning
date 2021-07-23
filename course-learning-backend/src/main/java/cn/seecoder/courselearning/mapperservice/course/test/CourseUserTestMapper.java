package cn.seecoder.courselearning.mapperservice.course.test;

import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import org.springframework.stereotype.Repository;

//Unit Test Finished
@Repository
public interface CourseUserTestMapper {
    // 参与测试则将用户id和测试id加入user_test表
    void finishTest(CourseUserTestPO courseUserTestPO);

    // 更新用户测试的分数
    void updateTest(CourseUserTestPO courseUserTestPO);
}
