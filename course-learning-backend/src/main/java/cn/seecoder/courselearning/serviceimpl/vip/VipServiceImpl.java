package cn.seecoder.courselearning.serviceimpl.vip;

import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.enums.HttpStatus;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.vip.VipService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.vip.VipChargeVO;
import cn.seecoder.courselearning.vo.vip.VipInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultVO<List<VipInfoVO>> getVipPriceInfo() {
        return new ResultVO<>(SimpleVipFactory.getVipPrice());
    }

    /**
     * 1.通过vip的种类判断价格，时间
     * 2.判断用户余额是否足够支付
     * 3.判断用户是否是vip
     *
     * @param vipChargeDTO
     * @return
     */
    @Override
    public ResultVO<VipChargeVO> getVip(VipChargeDTO vipChargeDTO) {
        //通过vip的种类判断价格，时间
        VipChargeVO vipChargeVO = SimpleVipFactory.getVipByType(vipChargeDTO);
        User user = userMapper.selectByPrimaryKey(vipChargeDTO.getUserId());
        //判断用户余额是否足够支付
        if (user.getBalance() >= vipChargeVO.getCost()) {
            user.setBalance(user.getBalance() - vipChargeVO.getCost());
            //判断用户是否是vip
            if (user.getIsVip() == 1) {
                user.setVipEndTime(new Date(user.getVipEndTime().getTime() + vipChargeVO.getEndTime().getTime()));
            } else {
                user.setVipEndTime(new Date(System.currentTimeMillis() + vipChargeVO.getEndTime().getTime()));
            }
            user.becomeVip();
            vipChargeVO.setEndTime(user.getVipEndTime());
            vipChargeVO.setBalance(user.getBalance());
            userMapper.updateByPrimaryKey(user);
            userMapper.decreaseBalance(user.getId(), vipChargeVO.getCost());
            return new ResultVO<>(vipChargeVO);
        } else {
            return ResultVO.fail(HttpStatus.USER_MONEY_NOT_ENOUGH);
        }
    }
}
