package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QueryOrderServiceImpl implements QueryOrderService {

    @Autowired
    CourseOrderMapper courseOrderMapper;

    @Override
    public CourseOrder queryMostRecentOrder(Integer uid, Integer courseId) {
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setUserId(uid);
        courseOrder.setCourseId(courseId);
        return courseOrderMapper.selectEndTime(courseOrder);
    }

    @Override
    public CourseOrder getByPrimaryKey(Integer orderId) {
        return courseOrderMapper.selectByPrimaryKey(orderId);
    }
}
