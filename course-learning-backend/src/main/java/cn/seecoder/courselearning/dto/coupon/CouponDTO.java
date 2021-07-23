package cn.seecoder.courselearning.dto.coupon;

import cn.seecoder.courselearning.enums.CouponScope;
import cn.seecoder.courselearning.enums.CouponType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 优惠券/优惠活动DTO类
 */

//数据转换类 类似vo po，但也有些区别
@Data
public class CouponDTO {
    private CouponType type;
    private CouponScope scope;
    private String name;
    private String description;
    private String metadata;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean sharable;
    // 发放策略
    private DeliverCouponDTO deliverCouponDTO;
}
