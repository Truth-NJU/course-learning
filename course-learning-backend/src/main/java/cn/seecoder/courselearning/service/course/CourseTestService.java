package cn.seecoder.courselearning.service.course;

import cn.seecoder.courselearning.dto.course.*;
import cn.seecoder.courselearning.po.course.test.CourseTestQuestionPO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.test.CourseTestFinishVO;
import cn.seecoder.courselearning.vo.course.test.CourseQuestionVO;
import cn.seecoder.courselearning.vo.course.test.CourseTestVO;

import java.util.List;

public interface CourseTestService {

    ResultVO<CourseQuestionVO> createQuestion(CourseQuestionDTO courseQuestionDTO);

    ResultVO<List<CourseQuestionVO>> getQuestionsByCourseId(int courseId);

    ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(int testId, int userId);


    ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(int testId);

    ResultVO<CourseQuestionVO> fixQuestion(CourseQuestionDTO courseQuestionDTO);

    ResultVO deleteQuestion(CourseQuestionDTO courseQuestionDTO);

    ResultVO deleteTest(CourseTestDTO courseTestDTO);

    ResultVO<CourseTestVO> fixTest(CourseTestDTO courseTestDTO);

    ResultVO<CourseTestVO> createTest(CourseTestDTO courseTestDTO);

    ResultVO<List<CourseTestVO>> getTestsByCourseId(int courseId);

    ResultVO<List<CourseTestVO>> getTestsByCourseId(int courseId, int userId);

    ResultVO<List<CourseTestVO>> getTestsByTeacherId(int teacherId);

    ResultVO<CourseTestFinishVO> finishTest(CourseTestFinishDTO courseTestFinishDTO);

    ResultVO<List<CourseTestQuestionPO>> addTestQuestion(CourseTestQuestionAddDTO courseTestQuestionAddDTO);

    ResultVO deleteTestQuestion(CourseTestQuestionDeleteDTO courseTestQuestionDeleteDTO);
}
