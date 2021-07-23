package cn.seecoder.courselearning.dto.course;

import lombok.Getter;

@Getter
public class CourseTestQuestionDeleteDTO {
    private Integer testId;

    private Integer questionId;

    public CourseTestQuestionDeleteDTO(){}

    public CourseTestQuestionDeleteDTO(Integer testId, Integer questionId) {
        this.questionId = questionId;
        this.testId = testId;
    }
}
