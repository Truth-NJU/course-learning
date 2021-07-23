package cn.seecoder.courselearning.dto.order;

import lombok.Getter;

@Getter
public class OrderRentDTO {

    private int userId;

    private  int courseId;

    private  int time;

    public OrderRentDTO() {
    }

    public OrderRentDTO(int userId, int courseId, int time) {
        this.userId = userId;
        this.courseId = courseId;
        this.time = time;
    }
}
