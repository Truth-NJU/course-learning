package cn.seecoder.courselearning.vo.course.test;

import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CourseTestFinishVO {

    private int testId;

    private int score;


    public CourseTestFinishVO(CourseUserTestPO courseUserTestPO) {

        testId = courseUserTestPO.getTestId();

        score = courseUserTestPO.getScore();

    }

    @Override
    public String toString() {
        return "CourseTestFinishVO{" +
                "testId=" + testId +
                ", score=" + score +
                '}';
    }
}
