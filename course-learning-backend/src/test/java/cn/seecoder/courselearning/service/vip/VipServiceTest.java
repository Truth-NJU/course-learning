package cn.seecoder.courselearning.service.vip;

import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.vip.VipInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class VipServiceTest {

    @Autowired
    VipService vipService;

    @Test
    void getVipPriceInfo() {
        ResultVO<List<VipInfoVO>> resultVO = vipService.getVipPriceInfo();
        for (VipInfoVO vipInfoVO : resultVO.getData()) {
            System.out.println(vipInfoVO.toString());
        }
    }

    @Test
    void getVip() {
        VipChargeDTO vipChargeDTO = new VipChargeDTO(1, "week");
        System.out.println(vipService.getVip(vipChargeDTO).toString());
    }



}