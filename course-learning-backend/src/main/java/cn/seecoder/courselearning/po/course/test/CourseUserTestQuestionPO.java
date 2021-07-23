package cn.seecoder.courselearning.po.course.test;

import cn.seecoder.courselearning.dto.course.CourseTestFinishDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CourseUserTestQuestionPO {

    // 用户id
    int userId;
    // 用户作答的题目id
    int questionId;
    // 测试id
    int testId;
    // 用户的答案
    String answer;
    // 答案正确与否 0错误，1正确
    int userOx;

    public CourseUserTestQuestionPO() {

    }

    public CourseUserTestQuestionPO(int userId, int questionId, int testId, String answer, int userOx) {
        this.userId = userId;
        this.questionId = questionId;
        this.testId = testId;
        this.answer = answer;
        this.userOx = userOx;
    }

    @Override
    public String toString() {
        return "CourseUserTestQuestionPO{" +
                "userId=" + userId +
                ", questionId=" + questionId +
                ", testId=" + testId +
                ", answer='" + answer + '\'' +
                ", userOx=" + userOx +
                '}';
    }

    public static List<CourseUserTestQuestionPO> transferFromDTO(CourseTestFinishDTO courseTestFinishDTO) {
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : courseTestFinishDTO.getQuestionIdAnswers().entrySet()) {
            // key是questionId
            // value是学生的answer
            courseUserTestQuestionPOList.add(new CourseUserTestQuestionPO( courseTestFinishDTO.getUserId(),entry.getKey(), courseTestFinishDTO.getTestId(), entry.getValue()));
        }
        return courseUserTestQuestionPOList;
    }

    public CourseUserTestQuestionPO(int userId, int questionId, int testId, String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.testId = testId;
        this.answer = answer;
    }

}
