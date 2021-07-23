package cn.seecoder.courselearning.mapperservice.order;

import cn.seecoder.courselearning.po.order.RechargeOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RechargeOrderMapper")
public interface RechargeOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer orderId);

    List<RechargeOrder> selectAll();

    int updateByPrimaryKey(RechargeOrder record);
}