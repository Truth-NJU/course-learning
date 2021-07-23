package cn.seecoder.courselearning.mapperservice.order;

import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

//Unit Test Has Finished
@Repository("CourseOrderMapper")
public interface CourseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    // 插入订单信息
    int insert(CourseOrder record);

    //根据user_id 和 course_id 获得最近的订单的purchaseType
    CourseOrder selectPurchaseType(CourseOrder courseOrder);

    //根据user_id 和 course_id 获得最大的end_time
    CourseOrder selectEndTime(CourseOrder courseOrder);

    CourseOrder selectByPrimaryKey(Integer id);

    List<CourseOrder> selectAll();

    int updateByPrimaryKey(CourseOrder record);

    List<CourseOrder> selectByUserId(Integer userId);

    CourseOrder queryMostRecentOrder(Integer userId, Integer courseId);

    CourseOrder getIfBuy(Integer userId, Integer courseId);
}