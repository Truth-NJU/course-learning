package cn.seecoder.courselearning.mapperservice.coupon;

import cn.seecoder.courselearning.po.coupon.CourseOrderCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CourseOrderCouponMapper")
public interface CourseOrderCouponMapper {
    int insert(CourseOrderCoupon courseOrderCoupon);

    List<CourseOrderCoupon> selectByOrderId(Integer orderId);

    CourseOrderCoupon selectByOrderIdAndCouponId(@Param(value = "orderId") Integer orderId, @Param(value = "couponId") Integer couponId);

    int deleteByOrderIdAndCouponId(@Param(value = "orderId") Integer orderId, @Param(value = "couponId") Integer couponId);
}
