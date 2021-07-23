package cn.seecoder.courselearning.dto.order;

import lombok.Getter;

@Getter
public class OrderSchoolbagDTO {

    private int userId;

    private  String schoolName;

    public OrderSchoolbagDTO() {
    }

    public OrderSchoolbagDTO(int userId, String schoolName) {
        this.userId = userId;
        this.schoolName = schoolName;
    }
}
