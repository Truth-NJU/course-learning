package cn.seecoder.courselearning.mapperservice.coupon.mapperStub;

import cn.seecoder.courselearning.mapperservice.coupon.CouponMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CouponMapperImplStub implements CouponMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Coupon record) {
        return 0;
    }

    @Override
    public Coupon selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<Coupon> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Coupon record) {
        return 0;
    }

    @Override
    public List<Coupon> selectByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<Coupon> selectByOrderId(Integer orderId) {
        return new ArrayList<>();
    }
}
