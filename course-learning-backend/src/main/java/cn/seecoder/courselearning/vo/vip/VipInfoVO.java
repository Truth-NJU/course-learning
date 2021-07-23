package cn.seecoder.courselearning.vo.vip;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VipInfoVO {
    int money;

    int time;

    String type;

    int id;

    public VipInfoVO(String type, int money, int time, int id) {
        this.type = type;
        this.money = money;
        this.time = time;
        this.id = id;
    }

    @Override
    public String toString() {
        return "VipInfoVO{" +
                "money=" + money +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
