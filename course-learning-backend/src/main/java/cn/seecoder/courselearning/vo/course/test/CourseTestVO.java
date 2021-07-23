package cn.seecoder.courselearning.vo.course.test;

import cn.seecoder.courselearning.enums.TestStatus;
import cn.seecoder.courselearning.po.course.test.CourseTestPO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CourseTestVO {

    Integer testId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date endTime;

    Integer courseId;

    String testName;

    TestStatus testStatus;

    Boolean isFinished;

    Integer score;

    public CourseTestVO(CourseTestPO courseTestPO) {
        testId = courseTestPO.getTestId();
        startTime = courseTestPO.getStartTime();
        endTime = courseTestPO.getEndTime();
        courseId = courseTestPO.getCourseId();
        testName = courseTestPO.getTestName();
        testStatus = courseTestPO.getTestStatus();
    }

    @Override
    public String toString() {
        return "CourseTestVO{" +
                "testId=" + testId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", courseId=" + courseId +
                ", testName='" + testName + '\'' +
                ", testStatus=" + testStatus +
                ", isFinished=" + isFinished +
                ", score=" + score +
                '}';
    }
}
