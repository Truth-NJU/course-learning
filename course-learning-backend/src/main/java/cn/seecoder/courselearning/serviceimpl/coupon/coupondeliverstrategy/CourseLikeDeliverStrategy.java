package cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy;

import cn.seecoder.courselearning.mapperservice.course.CourseLikesMapper;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.coupon.coupondeliverstrategy.CouponDeliverStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户点赞数发放策略
 */
@Component
public class CourseLikeDeliverStrategy implements CouponDeliverStrategy {

    @Autowired
    protected CourseLikesMapper courseLikesMapper;

    @Autowired
    protected CourseMapper courseMapper;

    @Autowired
    protected CourseOrderMapper courseOrderMapper;

    public Integer dxy = 100;

    /**
     * 这里实现的逻辑为
     *  1.如果买过该优惠券的课程，则不允许发放
     *  2.如果学生给与该优惠券课程同学校的其他课程点过赞，则允许发放
     *  3.如果学生给与该优惠券课程同教师的其他课程点过赞，则允许发放
     *
     * @param user   用户
     * @param coupon 优惠券
     * @param extra  额外的判断数据
     * @return
     */
    @Override
    public boolean canDeliver(User user, Coupon coupon, Map<String, String> extra) {
        String courseIdStr = extra.get("courseId");
        if (courseIdStr == null) return false;
        Integer courseId = Integer.parseInt(courseIdStr);

        CourseOrder if_buy = courseOrderMapper.getIfBuy(user.getId(), courseId);
        if (if_buy != null)
            return false;

        Course course = courseMapper.selectByPrimaryKey(courseId);
        String school = course.getSchool();
        List<Course> courses = courseMapper.selectBySchool(school);
        boolean flag = false;
        for (Course each : courses) {
            if (!each.equals(course)) {
                if (courseLikesMapper.count(user.getId(), each.getId()) == 1) {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            Integer teacherId = course.getTeacherId();
            courses = courseMapper.selectByTeacherId(teacherId);
            for (Course each : courses) {
                if (!each.equals(course)) {
                    if (courseLikesMapper.count(user.getId(), each.getId()) == 1) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
