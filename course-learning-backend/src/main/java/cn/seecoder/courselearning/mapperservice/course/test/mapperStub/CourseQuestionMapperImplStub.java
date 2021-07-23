package cn.seecoder.courselearning.mapperservice.course.test.mapperStub;

import cn.seecoder.courselearning.enums.QuestionType;
import cn.seecoder.courselearning.mapperservice.course.test.CourseQuestionMapper;
import cn.seecoder.courselearning.po.course.test.CourseQuestionPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CourseQuestionMapperImplStub implements CourseQuestionMapper {
    @Override
    public int createQuestion(CourseQuestionPO courseQuestionPO) {
        courseQuestionPO.setQuestionId(1);
        return 0;
    }

    @Override
    public int deleteQuestion(int questionId) {
        return 0;
    }

    @Override
    public CourseQuestionPO selectQuestionByQuestionId(int questionId) {
        return new CourseQuestionPO(1,"123","233","123",1,QuestionType.choice);
    }

    @Override
    public List<CourseQuestionPO> selectQuestionByTestId(int testId) {
        return selectQuestionByCourseId(1);
    }

    @Override
    public List<CourseQuestionPO> selectQuestionByCourseId(int courseId) {
        List<CourseQuestionPO> courseQuestionPOList = new ArrayList<>();
        courseQuestionPOList.add(new CourseQuestionPO(1, "123", "233", "789", 1, QuestionType.choice));
        courseQuestionPOList.add(new CourseQuestionPO(2, "1234", "4567", "7890", 1, QuestionType.ask));
        courseQuestionPOList.add(new CourseQuestionPO(3, "0123", "233", "6789", 1, QuestionType.choice));
        return courseQuestionPOList;
    }

    @Override
    public int updateQuestion(CourseQuestionPO courseQuestionPO) {
        return 0;
    }

    @Override
    public int addAnswer(CourseUserTestQuestionPO courseUserTestQuestionPO) {
        return 0;
    }

    @Override
    public int updateAnswer(CourseUserTestQuestionPO courseUserTestQuestionPO) {
        return 0;
    }
}
