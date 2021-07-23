package cn.seecoder.courselearning.po.course.test;

import cn.seecoder.courselearning.annotation.NotNull;
import cn.seecoder.courselearning.dto.course.CourseQuestionDTO;
import cn.seecoder.courselearning.enums.QuestionType;
import cn.seecoder.courselearning.po.CommonPO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class CourseQuestionPO extends CommonPO {
    // 题目编号
    Integer questionId;
    // 题干
    @NotNull
    String question;
    // 题目答案
    @NotNull
    String answer;
    // 题目解析
    @NotNull
    String analysis;
    // 课程id
    @NotNull
    Integer courseId;
    // 题目类型
    @NotNull
    QuestionType questionType;

    public CourseQuestionPO() {
    }

    @Override
    public String toString() {
        return "CourseQuestionPO{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", analysis='" + analysis + '\'' +
                ", courseId=" + courseId +
                ", questionType=" + questionType +
                '}';
    }

    public CourseQuestionPO(Integer questionId, String question, String answer, String analysis, Integer courseId, QuestionType questionType) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.analysis = analysis;
        this.courseId = courseId;
        this.questionType = questionType;
    }


    public CourseQuestionPO(CourseQuestionDTO courseQuestionDTO) {
        this.questionId = courseQuestionDTO.getQuestionId();
        this.question = courseQuestionDTO.getQuestion();
        this.answer = courseQuestionDTO.getQuestionAnswer();
        this.analysis = courseQuestionDTO.getQuestionAnalysis();
        this.courseId =courseQuestionDTO.getCourseId();
        this.questionType =courseQuestionDTO.getQuestionType();
    }

}
