package cn.seecoder.courselearning.dto.course;

import lombok.Getter;

import java.util.Map;

@Getter
public class CourseTestFinishDTO {

    private  int testId;

    private int userId;

    private  Map<Integer, String> questionIdAnswers;



    public CourseTestFinishDTO() {
    }

    public CourseTestFinishDTO(Integer testId, Integer userId, Map<Integer, String> questionIdAnswers) {
        this.testId = testId;
        this.userId = userId;
        this.questionIdAnswers = questionIdAnswers;
    }

}
