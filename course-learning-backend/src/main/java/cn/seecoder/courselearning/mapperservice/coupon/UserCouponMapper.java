package cn.seecoder.courselearning.mapperservice.coupon;

import cn.seecoder.courselearning.po.coupon.UserCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserCouponMapper")
public interface UserCouponMapper {

    int insert(UserCoupon userCoupon);

    int update(UserCoupon userCoupon);

    UserCoupon selectByCouponIdAndUserId(@Param(value = "couponId") Integer couponId, @Param(value = "userId")  Integer userId);

    List<UserCoupon> selectByUserId(Integer userId);

    void deleteByUserIdAndCouponId(@Param(value = "userId") Integer userId, @Param(value = "couponId") Integer couponId);
}
