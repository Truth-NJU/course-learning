package cn.seecoder.courselearning.mapperservice.order.mapperStub;

import cn.seecoder.courselearning.enums.PurchaseType;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.util.Constant;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CourseOrderImplStub implements CourseOrderMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(CourseOrder record) {
        record.setId(9);
        return 0;
    }

    @Override
    public CourseOrder selectPurchaseType(CourseOrder courseOrder) {
        return null;
    }

    @Override
    public CourseOrder selectEndTime(CourseOrder courseOrder) {
        return null;
    }

    @Override
    public CourseOrder selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<CourseOrder> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(CourseOrder record) {
        return 0;
    }

    @Override
    public List<CourseOrder> selectByUserId(Integer userId) {
        List<CourseOrder> courseOrders = new ArrayList<>();
        courseOrders.add(new CourseOrder(0, 3000, 2000, 1, "云原生", new Date(), 1, Constant.ORDER_STATUS_UNPAID, new Date(), PurchaseType.Rent));
        courseOrders.add(new CourseOrder(1, 3000, 2000, 2, "云原生1", new Date(), 1, Constant.ORDER_STATUS_SUCCESS, new Date(), PurchaseType.Buy));
        courseOrders.add(new CourseOrder(2, 3000, 2000, 3, "云原生2", new Date(), 1, Constant.ORDER_STATUS_SUCCESS, new Date(), PurchaseType.Rent));
        courseOrders.add(new CourseOrder(3, 3000, 2000, 4, "云原生3", new Date(), 1, Constant.ORDER_STATUS_UNPAID, new Date(), PurchaseType.Buy));

        return courseOrders;
    }

    @Override
    public CourseOrder queryMostRecentOrder(Integer userId, Integer courseId) {
        switch (courseId) {
            case 1:
                return new CourseOrder(0, 3000, 2000, 1, "云原生", new Date(), 1, Constant.ORDER_STATUS_UNPAID, new Date(), PurchaseType.Rent);
            case 2:
                return new CourseOrder(1, 3000, 2000, 2, "云原生1", new Date(), 1, Constant.ORDER_STATUS_SUCCESS, new Date(), PurchaseType.Buy);
            case 3:
                return new CourseOrder(2, 3000, 2000, 3, "云原生2", new Date(), 1, Constant.ORDER_STATUS_SUCCESS, new Date(System.currentTimeMillis() + 24 * 3600 * 1000), PurchaseType.Rent);
            case 4:
                return new CourseOrder(3, 3000, 2000, 4, "云原生3", new Date(), 1, Constant.ORDER_STATUS_UNPAID, new Date(), PurchaseType.Buy);
        }
        return null;
    }

    @Override
    public CourseOrder getIfBuy(Integer userId, Integer courseId) {
        return null;
    }
}
