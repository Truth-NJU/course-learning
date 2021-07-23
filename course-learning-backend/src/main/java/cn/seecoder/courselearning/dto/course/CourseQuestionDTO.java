package cn.seecoder.courselearning.dto.course;


import cn.seecoder.courselearning.enums.QuestionType;
import lombok.Getter;

@Getter
public class CourseQuestionDTO {

    private  String question;

    private Integer questionId;

    private  String questionAnswer;

    private String questionAnalysis;

    private Integer courseId;

    private QuestionType questionType;

    public CourseQuestionDTO() {
    }

    public CourseQuestionDTO(Integer courseId, String question, Integer questionId, String questionAnswer, String questionAnalysis, QuestionType questionType) {
        this.courseId=courseId;
        this.question=question;
        this.questionId = questionId;
        this.questionAnswer=questionAnswer;
        this.questionAnalysis=questionAnalysis;
        this.questionType = questionType;
    }
}
