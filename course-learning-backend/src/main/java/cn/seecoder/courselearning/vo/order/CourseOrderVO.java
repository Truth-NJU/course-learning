package cn.seecoder.courselearning.vo.order;

import cn.seecoder.courselearning.enums.PurchaseType;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class CourseOrderVO {
    private Integer id;

    // 课程原价
    private Integer origin;

    // 实际支付金额
    private Integer cost;

    private Integer courseId;

    private String courseName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer userId;

    private Integer status;

    // 课程使用权到期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private Integer time;

    // 0代表短期租用，1代表长期购买
    private PurchaseType purchaseType;

    // 目前已经使用的优惠券
    private List<CouponVO> usedCoupons;

    public CourseOrderVO() {
    }

    public CourseOrderVO(@NonNull CourseOrder order) {
        id = order.getId();
        origin = order.getOrigin();
        cost = order.getCost();
        courseId = order.getCourseId();
        courseName = order.getCourseName();
        createTime = order.getCreateTime();
        userId = order.getUserId();
        status = order.getStatus();
        endTime = order.getEndTime();
        purchaseType = order.getPurchaseType();
        if (purchaseType.equals(PurchaseType.Rent))
            time = Math.toIntExact((endTime.getTime() - createTime.getTime()) / 1000 / 3600 / 24);
    }
}
