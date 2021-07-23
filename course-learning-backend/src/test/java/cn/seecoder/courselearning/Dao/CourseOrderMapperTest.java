package cn.seecoder.courselearning.Dao;

import cn.seecoder.courselearning.enums.PurchaseType;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.order.CourseOrder;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseOrderMapperTest {
    @Autowired
    CourseOrderMapper courseOrderMapper;

    @Test
    void insert() {
        CourseOrder courseOrder=new CourseOrder(0,3000,2000,1,"云原生",new Date(),1,1,new Date(), PurchaseType.Rent);
        assert 1==courseOrderMapper.insert(courseOrder);
    }



    @Test
    void selectPurchaseType(){
        CourseOrder courseOrder=new CourseOrder(1,3000,2000,1,"云原生",new Date(),1,1,new Date(),PurchaseType.Buy);
        System.out.println(courseOrderMapper.selectPurchaseType(courseOrder).toString());
    }

    @Test
    void selectEndTime(){
        CourseOrder courseOrder=new CourseOrder(1,3000,2000,1,"云原生",new Date(),1,1,new Date(),PurchaseType.Buy);
        System.out.println(courseOrderMapper.selectEndTime(courseOrder).toString());
    }


}