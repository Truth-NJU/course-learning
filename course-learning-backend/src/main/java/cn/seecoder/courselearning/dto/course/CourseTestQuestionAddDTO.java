package cn.seecoder.courselearning.dto.course;

import lombok.Getter;

import java.util.List;

@Getter
public class CourseTestQuestionAddDTO {

    private  int testId;

    private List<Integer> questionIdList;

    public CourseTestQuestionAddDTO() {
    }

    public CourseTestQuestionAddDTO(int testId, List<Integer> questionIdList) {
        this.testId = testId;
        this.questionIdList = questionIdList;
    }

}
