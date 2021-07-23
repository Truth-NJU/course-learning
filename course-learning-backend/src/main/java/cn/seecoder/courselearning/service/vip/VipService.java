package cn.seecoder.courselearning.service.vip;

import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.vo.ResultVO;

import cn.seecoder.courselearning.vo.vip.VipChargeVO;
import cn.seecoder.courselearning.vo.vip.VipInfoVO;

import java.util.List;

public interface VipService {
    /**
     * 获得全部vip购买的信息
     * @return
     */
    public ResultVO<List<VipInfoVO>> getVipPriceInfo();

    /**
     * 用户购买vip
     * @param vipChargeDTO
     * @return
     */
    public ResultVO<VipChargeVO> getVip(VipChargeDTO vipChargeDTO);
}
