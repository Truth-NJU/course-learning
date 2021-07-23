package cn.seecoder.courselearning.controller.vip;


import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.service.vip.VipService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.vip.VipChargeVO;
import cn.seecoder.courselearning.vo.vip.VipInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    VipService vipService;

    /**
     * 用户查看各种vip的价格
     * @return
     */
    @ResponseBody
    @GetMapping("/price")
    public ResultVO<List<VipInfoVO>> getVipPriceInfo(){
        return vipService.getVipPriceInfo();
    }

    /**
     * 用户购买vip
     * @param vipChargeDTO
     * @return
     */
    @PostMapping("/get")
    public ResultVO<VipChargeVO> chargeVip(@RequestBody VipChargeDTO vipChargeDTO){
        return vipService.getVip(vipChargeDTO);
    }
}
