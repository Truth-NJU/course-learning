package cn.seecoder.courselearning.controller.course;

import cn.seecoder.courselearning.dto.course.*;
import cn.seecoder.courselearning.service.course.CourseTestService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.test.CourseQuestionVO;
import cn.seecoder.courselearning.vo.course.test.CourseTestVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course-test")
public class CourseTestController {
    @Resource
    CourseTestService courseTestService;

    /**
     * 教师为一门课程创建题目
     * @param courseQuestionDTO
     * @return
     */
    @PostMapping("/questionCreate")
    ResultVO<CourseQuestionVO> createQuestion(@RequestBody CourseQuestionDTO courseQuestionDTO) {
        return courseTestService.createQuestion(courseQuestionDTO);
    }

    /**
     * 教师修改一门课程的某个题目
     * @param courseQuestionDTO
     * @return
     */
    @PostMapping("/questionFix")
    ResultVO<CourseQuestionVO> fixQuestion(@RequestBody CourseQuestionDTO courseQuestionDTO) {
        return courseTestService.fixQuestion(courseQuestionDTO);
    }

    /**
     * 教师删除一门课程的某个题目
     * @param courseQuestionDTO
     * @return
     */
    @PostMapping("/questionDelete")
    ResultVO deleteQuestion(@RequestBody CourseQuestionDTO courseQuestionDTO) {
        return courseTestService.deleteQuestion(courseQuestionDTO);
    }

    /**
     * 教师通过课程Id查找该课程的全部题目
     * @param courseId
     * @return
     */
    @GetMapping("/questionGet/course/{courseId}")
    ResultVO<List<CourseQuestionVO>> getQuestionsByCourseId(@PathVariable int courseId) {
        return courseTestService.getQuestionsByCourseId(courseId);
    }

    /**
     * 学生查询测试的题目，
     * 若已做，则返回每题成绩，答案，解析
     * 若未做，则仅返回题目
     * @param testId
     * @param userId
     * @return
     */
    @GetMapping("/questionGet/test")
    ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(@RequestParam int testId, @RequestParam int userId) {
        return courseTestService.getQuestionsByTestId(testId, userId);
    }


    /**
     * 老师查询测试的题目
     * 返回每题的全部信息
     * @param testId
     * @return
     */
    @GetMapping("/questionGet/test/{testId}")
    ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(@PathVariable int testId) {
        return courseTestService.getQuestionsByTestId(testId);
    }

    /**
     * 教师为一门测试添加题目
     * @param courseTestQuestionAddDTO
     * @return
     */
    @PostMapping("/testAddQuestion")
    ResultVO addTestQuestion(@RequestBody CourseTestQuestionAddDTO courseTestQuestionAddDTO) {
        return courseTestService.addTestQuestion(courseTestQuestionAddDTO);
    }

    /**
     * 教师为一门测试删除题目
     * @param courseTestQuestionDeleteDTO
     * @return
     */
    @PostMapping("/testDeleteQuestion")
    ResultVO deleteTestQuestion(@RequestBody CourseTestQuestionDeleteDTO courseTestQuestionDeleteDTO) {
        return courseTestService.deleteTestQuestion(courseTestQuestionDeleteDTO);
    }

    /**
     * 学生完成一门测试
     * @param courseTestFinishDTO
     * @return
     */
    @PostMapping("/testAnswerCommit")
    ResultVO finishTest(@RequestBody CourseTestFinishDTO courseTestFinishDTO) {
        return courseTestService.finishTest(courseTestFinishDTO);
    }

    /**
     * 教师创建一门测试
     * @param courseTestDTO
     * @return
     */
    @PostMapping("/testCreate")
    ResultVO<CourseTestVO> createTest(@RequestBody CourseTestDTO courseTestDTO) {
        return courseTestService.createTest(courseTestDTO);
    }

    /**
     * 教师修改测试信息
     * @param courseTestDTO
     * @return
     */
    @PostMapping("/testFix")
    ResultVO<CourseTestVO> fixTest(@RequestBody CourseTestDTO courseTestDTO) {
        return courseTestService.fixTest(courseTestDTO);
    }

    /**
     * 教师删除某个测试
     * @param courseTestDTO
     * @return
     */
    @PostMapping("/testDelete")
    ResultVO deleteQuestion(@RequestBody CourseTestDTO courseTestDTO) {
        return courseTestService.deleteTest(courseTestDTO);
    }

    /**
     * 老师获得自己创建的全部测试
     * @param teacherId
     * @return
     */
    @GetMapping("/testGet/teacher/{teacherId}")
    ResultVO<List<CourseTestVO>> getTestsByTeacherId(@PathVariable int teacherId) {
        return courseTestService.getTestsByTeacherId(teacherId);
    }

    /**
     * 学生查询一个课程的全部测试，需要返回测试成绩
     *
     * @param courseId
     * @param userId
     * @return
     */
    @GetMapping("/testGet/course")
    ResultVO<List<CourseTestVO>> getTestsByCourseId(@RequestParam int courseId, @RequestParam int userId) {
        return courseTestService.getTestsByCourseId(courseId, userId);
    }

    /**
     * 老师查询一个课程的全部测试
     *
     * @param courseId
     * @return
     */
    @GetMapping("/testGet/course/{courseId}")
    ResultVO<List<CourseTestVO>> getTestsByCourseId(@PathVariable int courseId) {
        return courseTestService.getTestsByCourseId(courseId);
    }
}
