package cn.seecoder.courselearning.po.course.test;

import cn.seecoder.courselearning.annotation.NotNull;
import cn.seecoder.courselearning.dto.course.CourseTestDTO;
import cn.seecoder.courselearning.enums.TestStatus;
import cn.seecoder.courselearning.po.CommonPO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseTestPO extends CommonPO {

    Integer testId;

    @NotNull
    Date startTime;

    @NotNull
    Date endTime;

    @NotNull
    Integer courseId;

    @NotNull
    String testName;

    @NotNull
    TestStatus testStatus;

    public CourseTestPO() {
    }

    public CourseTestPO(Integer testId, Date startTime, Date endTime, Integer courseId, String testName, TestStatus testStatus) {
        this.testId = testId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
        this.testName = testName;
        this.testStatus = testStatus;
    }

    public CourseTestPO(CourseTestDTO courseTestDTO) {
        testId = courseTestDTO.getTestId();
        startTime = courseTestDTO.getStartTime();
        endTime = courseTestDTO.getEndTime();
        courseId = courseTestDTO.getCourseId();
        testName = courseTestDTO.getTest();
    }


}
