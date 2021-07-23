package cn.seecoder.courselearning.dto.course;

import cn.seecoder.courselearning.enums.TestStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CourseTestDTO {

    private  Integer courseId;

    private  Integer testId;

    private String test;

    private ArrayList<Integer> questionsId;

    private Date startTime;

    private  Date endTime;

    public CourseTestDTO() {
    }

    public CourseTestDTO(Integer courseId, Integer testId, String test, ArrayList<Integer> questionsId, Date startTime, Date endTime) {
        this.courseId = courseId;
        this.testId = testId;
        this.test = test;
        this.questionsId =questionsId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
