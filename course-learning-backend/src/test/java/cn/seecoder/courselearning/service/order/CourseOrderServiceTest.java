package cn.seecoder.courselearning.service.order;

import cn.seecoder.courselearning.dto.order.OrderRentDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CourseOrderServiceTest {

    @Autowired
    CourseOrderService courseOrderService;

    @Test
    void getCourseOrders() {
        System.out.println(courseOrderService.getCourseOrders(1));
    }


    @Test
    void createCourseOrder() {
        //ResultVO{code=1, msg='恢复到未完成订单',data='CourseOrderVO(id=0, origin=3000, cost=2000, courseId=1, courseName=云原生
        System.out.println(courseOrderService.createCourseOrder(1,1));
        //ResultVO{code=0, msg='已购买该课程'}
        System.out.println(courseOrderService.createCourseOrder(2,1));
        //ResultVO{code=1, msg='恢复到未完成订单',data='CourseOrderVO(id=2, origin=3000, cost=2000, courseId=3, courseName=云原生2
        System.out.println(courseOrderService.createCourseOrder(3,1));
        //ResultVO{code=1, msg='恢复到未完成订单',data='CourseOrderVO(id=3, origin=3000, cost=2000, courseId=4, courseName=云原生3
        System.out.println(courseOrderService.createCourseOrder(4,1));
    }

    @Test
    void createCourseRentOrder() {
        //ResultVO{code=2001, msg='ok',data='CourseOrderVO(id=9, origin=null, cost=9, courseId=1, courseName=dxy, createTime=Fri Jul 09 16:06:24 CST 2021, userId=1, status=2, endTime=Mon Jul 19 16:06:24 CST 2021, purchaseType=Rent, usedCoupons=null)}
        System.out.println(courseOrderService.createCourseRentOrder(new OrderRentDTO(1,1,10)));
        //ResultVO{code=0, msg='已购买该课程'}
        System.out.println(courseOrderService.createCourseRentOrder(new OrderRentDTO(1,2,20)));
        //ResultVO{code=2001, msg='ok',data='CourseOrderVO(id=9, origin=null, cost=25, courseId=3, courseName=dxy, createTime=Fri Jul 09 16:06:24 CST 2021, userId=1, status=2, endTime=Mon Aug 09 16:06:24 CST 2021, purchaseType=Rent, usedCoupons=null)}
        System.out.println(courseOrderService.createCourseRentOrder(new OrderRentDTO(1,3,30)));
        //ResultVO{code=2001, msg='ok',data='CourseOrderVO(id=9, origin=null, cost=31, courseId=4, courseName=dxy, createTime=Fri Jul 09 16:06:24 CST 2021, userId=1, status=2, endTime=Wed Aug 18 16:06:24 CST 2021, purchaseType=Rent, usedCoupons=null)}
        System.out.println(courseOrderService.createCourseRentOrder(new OrderRentDTO(1,4,40)));
        //ResultVO{code=2001, msg='ok',data='CourseOrderVO(id=9, origin=null, cost=36, courseId=5, courseName=dxy, createTime=Fri Jul 09 16:06:24 CST 2021, userId=1, status=2, endTime=Sat Aug 28 16:06:24 CST 2021, purchaseType=Rent, usedCoupons=null)}
        System.out.println(courseOrderService.createCourseRentOrder(new OrderRentDTO(1,5,50)));

    }
}