package cn.seecoder.courselearning.serviceimpl.course;

import cn.seecoder.courselearning.dto.course.*;
import cn.seecoder.courselearning.enums.HttpStatus;
import cn.seecoder.courselearning.enums.TestStatus;
import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.course.test.*;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.course.*;
import cn.seecoder.courselearning.po.course.test.*;
import cn.seecoder.courselearning.service.course.CourseTestService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.test.CourseTestFinishVO;
import cn.seecoder.courselearning.vo.course.test.CourseQuestionVO;
import cn.seecoder.courselearning.vo.course.test.CourseTestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CourseTestServiceImpl implements CourseTestService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CourseQuestionMapper courseQuestionMapper;

    @Autowired
    CourseTestMapper courseTestMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseUserTestMapper courseUserTestMapper;

    @Autowired
    CourseTestQuestionMapper courseTestQuestionMapper;

    @Autowired
    CourseUserTestQuestionMapper courseUserTestQuestionMapper;

    @Override
    public ResultVO<CourseQuestionVO> createQuestion(CourseQuestionDTO courseQuestionDTO) {
        CourseQuestionPO courseQuestionPO = new CourseQuestionPO(courseQuestionDTO);
        if(!courseQuestionPO.isFull()){
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        courseQuestionMapper.createQuestion(courseQuestionPO);
        CourseQuestionVO courseQuestionVO = new CourseQuestionVO(courseQuestionPO);
        return new ResultVO<>(courseQuestionVO);
    }

    @Override
    public ResultVO<List<CourseQuestionVO>> getQuestionsByCourseId(int courseId) {
        List<CourseQuestionPO> courseQuestionPOList = courseQuestionMapper.selectQuestionByCourseId(courseId);
        List<CourseQuestionVO> courseQuestionVOList = new ArrayList<>();
        for (CourseQuestionPO courseQuestionPO : courseQuestionPOList) {
            courseQuestionVOList.add(new CourseQuestionVO(courseQuestionPO));
        }
        return new ResultVO<>(courseQuestionVOList);
    }

    @Override
    public ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(int testId) {
        List<CourseQuestionPO> courseQuestionPOList = courseQuestionMapper.selectQuestionByTestId(testId);
        List<CourseQuestionVO> courseQuestionVOList = new ArrayList<>();
        for (CourseQuestionPO courseQuestionPO : courseQuestionPOList) {
            courseQuestionVOList.add(new CourseQuestionVO(courseQuestionPO));
        }
        return new ResultVO<>(courseQuestionVOList);
    }

    @Override
    public ResultVO<List<CourseQuestionVO>> getQuestionsByTestId(int testId, int userId) {
        List<CourseQuestionPO> courseQuestionPOList = courseQuestionMapper.selectQuestionByTestId(testId);
        List<CourseQuestionVO> courseQuestionVOList = new ArrayList<>();
        //??????????????????????????????
        boolean userIfPassTest = false;
        boolean testIsFinish = courseTestMapper.selectTestById(testId).getTestStatus().equals(TestStatus.Finish);
        CourseUserTestPO courseUserTestPO = new CourseUserTestPO(userId, testId);
        userIfPassTest = (null != (courseUserTestPO = courseTestMapper.findMyScore(courseUserTestPO)));
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOList = new ArrayList<>();
        if (userIfPassTest)
            courseUserTestQuestionPOList = courseUserTestQuestionMapper.selectTestQuestions(courseUserTestPO);

        for (CourseQuestionPO courseQuestionPO : courseQuestionPOList) {
            CourseQuestionVO courseQuestionVO=new CourseQuestionVO(courseQuestionPO);
            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            if (userIfPassTest) {
                for (CourseUserTestQuestionPO courseUserTestQuestionPO : courseUserTestQuestionPOList) {
                    if (courseQuestionVO.getQuestionId() == courseUserTestQuestionPO.getQuestionId()) {
                        courseQuestionVO.setIsRight(courseUserTestQuestionPO.getUserOx() == 1);
                        courseQuestionVO.setStudentsAnswer(courseUserTestQuestionPO.getAnswer());
                        break;
                    }
                }
            }
            if(!testIsFinish){
                courseQuestionVO.setQuestionAnswer(null);
                courseQuestionVO.setQuestionAnalysis(null);
            }
            courseQuestionVOList.add(courseQuestionVO);
        }
        return new ResultVO<>(courseQuestionVOList);
    }

    @Override
    public ResultVO<CourseQuestionVO> fixQuestion(CourseQuestionDTO courseQuestionDTO) {
        CourseQuestionPO courseQuestionPO=new CourseQuestionPO(courseQuestionDTO);
        if(!courseQuestionPO.isFull() || courseQuestionPO.getQuestionId()==null){
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        courseQuestionMapper.updateQuestion(courseQuestionPO);
        CourseQuestionVO courseQuestionVO=new CourseQuestionVO(courseQuestionPO);
        return new ResultVO<>(courseQuestionVO);
    }

    @Override
    public ResultVO deleteQuestion(CourseQuestionDTO courseQuestionDTO) {
        courseQuestionMapper.deleteQuestion(courseQuestionDTO.getQuestionId());
        return ResultVO.succeed();
    }

    @Override
    public ResultVO deleteTest(CourseTestDTO courseTestDTO) {
        courseTestMapper.deleteTest(courseTestDTO.getTestId());
        courseTestQuestionMapper.deleteAllTestQuestion(courseTestDTO.getTestId());
        return ResultVO.succeed();
    }

    @Override
    public ResultVO<CourseTestVO> fixTest(CourseTestDTO courseTestDTO) {
        CourseTestPO courseTestPO = new CourseTestPO(courseTestDTO);
        if(!courseTestPO.isFull() || courseTestPO.getTestId()==null){
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        courseTestMapper.updateTest(courseTestPO);
        CourseTestVO courseTestVO = new CourseTestVO(courseTestPO);
        return new ResultVO<>(courseTestVO);
    }

    private boolean checkTestStatus(CourseTestPO courseTestPO) {
        if (null != courseTestPO.getTestStatus() && courseTestPO.getTestStatus().equals(TestStatus.Finish)) {
            return false;
        }
        if (new Date().before(courseTestPO.getStartTime())) {
            courseTestPO.setTestStatus(TestStatus.toStart);
        } else if (new Date().after(courseTestPO.getEndTime())) {
            courseTestPO.setTestStatus(TestStatus.Finish);
        } else {
            courseTestPO.setTestStatus(TestStatus.Running);
        }
        return true;
    }

    @Override
    public ResultVO<CourseTestVO> createTest(CourseTestDTO courseTestDTO) {
        CourseTestPO courseTestPO = new CourseTestPO(courseTestDTO);
        if(!courseTestPO.isFull()){
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        checkTestStatus(courseTestPO);
        courseTestMapper.createTest(courseTestPO);
        CourseTestVO courseTestVO = new CourseTestVO(courseTestPO);
        return new ResultVO<>(courseTestVO);
    }

    @Override
    public ResultVO<List<CourseTestVO>> getTestsByCourseId(int courseId, int userId) {
        List<CourseTestPO> courseTestPOList = courseTestMapper.findAllTest(courseId);
        List<CourseTestVO> courseTestVOList = new ArrayList<>();
        for (CourseTestPO courseTestPO : courseTestPOList) {
            CourseTestVO courseTestVO = new CourseTestVO(courseTestPO);
            //??????test??????
            if (checkTestStatus(courseTestPO)) {
                courseTestMapper.updateTest(courseTestPO);
            }
            //??????????????????????????????????????????????????????
            CourseUserTestPO courseUserTestPO = courseTestMapper.findMyScore(new CourseUserTestPO(userId, courseTestPO.getTestId()));
            courseTestVO.setIsFinished(null != courseUserTestPO);
            if (courseTestVO.getIsFinished()) {
                courseTestVO.setScore(courseUserTestPO.getScore());
            }

            courseTestVOList.add(courseTestVO);
        }
        return new ResultVO<>(courseTestVOList);
    }

    @Override
    public ResultVO<List<CourseTestVO>> getTestsByCourseId(int courseId) {
        List<CourseTestPO> courseTestPOList = courseTestMapper.findAllTest(courseId);
        List<CourseTestVO> courseTestVOList = new ArrayList<>();
        for (CourseTestPO courseTestPO : courseTestPOList) {
            //??????test??????
            if (checkTestStatus(courseTestPO)) {
                courseTestMapper.updateTest(courseTestPO);
            }
            courseTestVOList.add(new CourseTestVO(courseTestPO));
        }
        return new ResultVO<>(courseTestVOList);
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????? ??? ??????
     * @param teacherId
     * @return
     */
    @Override
    public ResultVO<List<CourseTestVO>> getTestsByTeacherId(int teacherId) {
        List<Course> courseList = courseMapper.selectByTeacherId(teacherId);
        List<CourseTestVO> courseTestVOList = new ArrayList<>();
        for (Course course : courseList) {
            courseTestVOList.addAll(getTestsByCourseId(course.getId()).getData());
        }
        return new ResultVO<>(courseTestVOList);
    }

    @Override
    public ResultVO<CourseTestFinishVO> finishTest(CourseTestFinishDTO courseTestFinishDTO) {
        //???????????????????????????
        if (userMapper.selectByPrimaryKey(courseTestFinishDTO.getUserId()).getUserRole().equals(UserRole.STUDENT)) {
            //???????????????????????????????????????????????????????????????
            if (null == courseTestMapper.findMyScore(new CourseUserTestPO(courseTestFinishDTO.getUserId(), courseTestFinishDTO.getTestId()))) {
                courseUserTestMapper.finishTest(new CourseUserTestPO(courseTestFinishDTO));
                courseUserTestQuestionMapper.finishTestQuestions(CourseUserTestQuestionPO.transferFromDTO(courseTestFinishDTO));
            }
            CourseTestFinishVO courseTestFinishVO = checkAnswerAndCalculateScore(courseTestFinishDTO);
            return new ResultVO<>(courseTestFinishVO);
        }
        return ResultVO.fail(HttpStatus.TEST_USER_ROLE_ERROR);
    }

    @Override
    public ResultVO addTestQuestion(CourseTestQuestionAddDTO courseTestQuestionAddDTO) {
        List<CourseTestQuestionPO> courseTestQuestionPOList = CourseTestQuestionPO.transferFromDTO(courseTestQuestionAddDTO);
//        for(int i=0;i<courseTestQuestionPOList.size();i++){
//            if(!courseTestQuestionPOList.get(i).isFull()){
//                return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
//            }
//        }
        courseTestQuestionMapper.addTestQuestion(courseTestQuestionPOList);
        return ResultVO.succeed();
    }

    @Override
    public ResultVO deleteTestQuestion(CourseTestQuestionDeleteDTO courseTestQuestionDeleteDTO) {
        courseTestQuestionMapper.deleteTestQuestion(courseTestQuestionDeleteDTO.getTestId(),courseTestQuestionDeleteDTO.getQuestionId());
        return ResultVO.succeed();
    }

    private CourseTestFinishVO checkAnswerAndCalculateScore(CourseTestFinishDTO courseTestFinishDTO) {
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOList = new ArrayList<>();
        int correctNumber = 0;
        for (Map.Entry<Integer, String> entry : courseTestFinishDTO.getQuestionIdAnswers().entrySet()) {
            // key???questionId
            // value????????????answer
            int questionId = entry.getKey();
            String answer = entry.getValue();
            //??????questionId??????????????????
            CourseQuestionPO courseQuestionPO = courseQuestionMapper.selectQuestionByQuestionId(questionId);
            //??????????????????????????????
            if (courseQuestionPO.getAnswer().equals(answer)) {
                CourseUserTestQuestionPO courseUserTestQuestionPO = new CourseUserTestQuestionPO(courseTestFinishDTO.getUserId(), entry.getKey(), courseTestFinishDTO.getTestId(), entry.getValue(), 1);
                courseUserTestQuestionPOList.add(courseUserTestQuestionPO);
                correctNumber++;
            } else {
                CourseUserTestQuestionPO courseUserTestQuestionPO = new CourseUserTestQuestionPO(courseTestFinishDTO.getUserId(), entry.getKey(), courseTestFinishDTO.getTestId(), entry.getValue(), 0);
                courseUserTestQuestionPOList.add(courseUserTestQuestionPO);
            }
        }

        courseUserTestQuestionMapper.updateTestQuestions(courseUserTestQuestionPOList);

        //????????????
        int questionNumber = courseUserTestQuestionPOList.size();
        int score = (int) ((1.0 * correctNumber / questionNumber) * 100);

        CourseUserTestPO courseUserTestPO = new CourseUserTestPO(courseTestFinishDTO.getUserId(), courseTestFinishDTO.getTestId(), score);
        courseUserTestMapper.updateTest(courseUserTestPO);

        return new CourseTestFinishVO(courseUserTestPO);
    }
}
