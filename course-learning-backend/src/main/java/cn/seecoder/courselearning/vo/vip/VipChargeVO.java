package cn.seecoder.courselearning.vo.vip;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VipChargeVO {

    int cost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date endTime;

    String type;

    int balance;

    public VipChargeVO() {

    }

    public VipChargeVO(String type, int cost, Date endTime) {
        this.type = type;
        this.cost = cost;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "VipChargeVO{" +
                "cost=" + cost +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }
}
