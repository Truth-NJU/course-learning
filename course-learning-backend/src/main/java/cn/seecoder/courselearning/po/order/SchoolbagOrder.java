package cn.seecoder.courselearning.po.order;

import cn.seecoder.courselearning.dto.order.OrderSchoolbagDTO;

public class SchoolbagOrder {
    int userId;

    String schoolName;

    public SchoolbagOrder(OrderSchoolbagDTO orderSchoolbagDTO) {
        userId = orderSchoolbagDTO.getUserId();
        schoolName = orderSchoolbagDTO.getSchoolName();
    }
}
