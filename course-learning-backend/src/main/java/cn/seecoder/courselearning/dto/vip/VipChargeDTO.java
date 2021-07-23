package cn.seecoder.courselearning.dto.vip;

import lombok.Getter;

@Getter
public class VipChargeDTO {

    private  int userId;

    private  String type;

    public VipChargeDTO() {
    }

    public VipChargeDTO(int userId, String type) {
        this.userId = userId;
        this.type = type;
    }

}
