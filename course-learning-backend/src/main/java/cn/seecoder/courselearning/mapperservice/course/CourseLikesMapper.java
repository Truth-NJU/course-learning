package cn.seecoder.courselearning.mapperservice.course;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("CourseLikesMapper")
public interface CourseLikesMapper {
    int deleteByPrimaryKey(@Param("courseId") Integer courseId, @Param("userId") Integer userId);

    int insert(@Param("courseId") Integer courseId, @Param("userId") Integer userId);

    int count(@Param("courseId") Integer courseId, @Param("userId") Integer userId);

}
