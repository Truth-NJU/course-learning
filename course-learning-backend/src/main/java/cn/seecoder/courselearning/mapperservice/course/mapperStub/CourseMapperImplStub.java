package cn.seecoder.courselearning.mapperservice.course.mapperStub;

import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.po.course.Course;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

public class CourseMapperImplStub implements CourseMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Course record) {
        return 0;
    }

    @Override
    public Course selectByPrimaryKey(Integer id) {
        Course course=new Course();
        course.setCost(1666);
        course.setName("dxy");
        return course;
    }

    @Override
    public List<Course> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return 0;
    }

    @Override
    public List<Course> selectByType(String type) {
        return null;
    }

    @Override
    public List<Course> queryAll(String key) {
        return null;
    }

    @Override
    public List<Course> selectByTeacherId(Integer userId) {
        return null;
    }

    @Override
    public List<Course> selectByStudentId(Integer userId) {
        return null;
    }

    @Override
    public List<Course> selectHotCourses() {
        return null;
    }

    @Override
    public List<Course> selectBySchool(String school) {
        return null;
    }
}
