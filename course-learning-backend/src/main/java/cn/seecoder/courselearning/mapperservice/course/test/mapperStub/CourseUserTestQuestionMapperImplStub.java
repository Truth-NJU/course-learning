package cn.seecoder.courselearning.mapperservice.course.test.mapperStub;

import cn.seecoder.courselearning.mapperservice.course.test.CourseUserTestQuestionMapper;
import cn.seecoder.courselearning.po.course.test.CourseUserTestPO;
import cn.seecoder.courselearning.po.course.test.CourseUserTestQuestionPO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CourseUserTestQuestionMapperImplStub implements CourseUserTestQuestionMapper {
    @Override
    public void finishTestQuestions(List<CourseUserTestQuestionPO> courseUserTestQuestionPOS) {

    }

    @Override
    public void updateTestQuestions(List<CourseUserTestQuestionPO> courseUserTestQuestionPOS) {

    }

    @Override
    public List<CourseUserTestQuestionPO> selectTestQuestions(CourseUserTestPO courseUserTestPO) {
        List<CourseUserTestQuestionPO> courseUserTestQuestionPOList=new ArrayList<>();
        courseUserTestQuestionPOList.add(new CourseUserTestQuestionPO(1,1,1,"233",1));
        courseUserTestQuestionPOList.add(new CourseUserTestQuestionPO(1,2,1,"233",0));
        courseUserTestQuestionPOList.add(new CourseUserTestQuestionPO(1,3,1,"12345",1));
        return courseUserTestQuestionPOList;
    }
}
