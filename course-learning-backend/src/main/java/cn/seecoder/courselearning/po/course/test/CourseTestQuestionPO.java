package cn.seecoder.courselearning.po.course.test;

import cn.seecoder.courselearning.annotation.NotNull;
import cn.seecoder.courselearning.dto.course.CourseTestQuestionAddDTO;
import cn.seecoder.courselearning.po.CommonPO;

import java.util.ArrayList;
import java.util.List;

public class CourseTestQuestionPO extends CommonPO {

    @NotNull
    int testId;

    @NotNull
    int questionId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public CourseTestQuestionPO() {
    }

    public static List<CourseTestQuestionPO> transferFromDTO(CourseTestQuestionAddDTO courseTestQuestionAddDTO) {
        List<CourseTestQuestionPO> courseTestQuestionPOList = new ArrayList<>();
        for (Integer questionId : courseTestQuestionAddDTO.getQuestionIdList()) {
            courseTestQuestionPOList.add(new CourseTestQuestionPO(courseTestQuestionAddDTO.getTestId(),questionId));
        }
        return courseTestQuestionPOList;
    }

    public CourseTestQuestionPO(int testId, int questionId) {
        this.testId = testId;
        this.questionId = questionId;
    }

}
