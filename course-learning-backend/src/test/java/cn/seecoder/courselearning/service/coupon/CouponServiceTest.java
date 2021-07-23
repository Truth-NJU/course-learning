package cn.seecoder.courselearning.service.coupon;

import cn.seecoder.courselearning.dto.coupon.DeliverCouponDTO;
import cn.seecoder.courselearning.enums.CouponDeliver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @Test
    void createCoupon() {

    }

    @Test
    void deliverCoupon() {
        Map<String, String> extras = new HashMap<>();
        extras.put("courseId", "1");
        DeliverCouponDTO del = new DeliverCouponDTO(1, CouponDeliver.COURSE_LIKE, true, 8, extras);
        couponService.deliverCoupon(del);
    }
}