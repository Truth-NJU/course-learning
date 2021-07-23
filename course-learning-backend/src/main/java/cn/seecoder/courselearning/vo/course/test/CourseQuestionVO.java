package cn.seecoder.courselearning.vo.course.test;

import cn.seecoder.courselearning.enums.QuestionType;
import cn.seecoder.courselearning.po.course.test.CourseQuestionPO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseQuestionVO {

    private String question;

    private Integer questionId;

    private String questionAnswer;

    private String questionAnalysis;

    private Integer courseId;

    private QuestionType questionType;

    private Boolean isRight;

    private String studentsAnswer;

    public CourseQuestionVO(CourseQuestionPO courseQuestionPO) {
        questionId = courseQuestionPO.getQuestionId();
        question = courseQuestionPO.getQuestion();
        questionAnswer = courseQuestionPO.getAnswer();
        questionAnalysis = courseQuestionPO.getAnalysis();
        courseId = courseQuestionPO.getCourseId();
        questionType = courseQuestionPO.getQuestionType();
    }

    @Override
    public String toString() {
        return "CourseQuestionVO{" +
                "question='" + question + '\'' +
                ", questionId=" + questionId +
                ", questionAnswer='" + questionAnswer + '\'' +
                ", questionAnalysis='" + questionAnalysis + '\'' +
                ", courseId=" + courseId +
                ", questionType=" + questionType +
                ", isRight=" + isRight +
                ", studentsAnswer='" + studentsAnswer + '\'' +
                '}';
    }
}
