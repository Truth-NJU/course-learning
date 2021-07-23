package cn.seecoder.courselearning.po.order;

import cn.seecoder.courselearning.enums.PurchaseType;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseOrder {
    private Integer id;

    private Integer origin;

    private Integer cost;

    private Integer courseId;

    private String courseName;

    private Date createTime;

    private Integer userId;

    private Integer status;

    // 课程使用权到期时间
    private Date endTime;

    // 0代表短期租用，1代表长期购买
    private PurchaseType purchaseType;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public CourseOrder() {
    }


    public CourseOrder(Integer id, Integer origin, Integer cost, Integer courseId, String courseName, Date createTime, Integer userId, Integer status, Date endTime, PurchaseType purchaseType) {
        this.id = id;
        this.origin = origin;
        this.cost = cost;
        this.courseId = courseId;
        this.courseName = courseName;
        this.createTime = createTime;
        this.userId = userId;
        this.status = status;
        this.endTime = endTime;
        this.purchaseType = purchaseType;
    }

    public CourseOrder( Integer origin, Integer cost, Integer courseId, String courseName, Date createTime, Integer userId, Integer status, Date endTime, PurchaseType purchaseType) {
        this.origin = origin;
        this.cost = cost;
        this.courseId = courseId;
        this.courseName = courseName;
        this.createTime = createTime;
        this.userId = userId;
        this.status = status;
        this.endTime = endTime;
        this.purchaseType = purchaseType;
    }

    @Override
    public String toString() {
        return "CourseOrder{" +
                "id=" + id +
                ", origin=" + origin +
                ", cost=" + cost +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", status=" + status +
                ", endTime=" + endTime +
                ", purchaseType=" + purchaseType +
                '}';
    }

    public boolean isValid() {
        return status.equals(Constant.ORDER_STATUS_SUCCESS);
    }

    public boolean isOnRent() {
        return isValid() && purchaseType.equals(PurchaseType.Rent) && endTime.after(new Date());
    }

    public boolean isBought() {
        return isValid() && purchaseType.equals(PurchaseType.Buy);
    }

    public boolean isOk() {
        return isBought() || isOnRent();
    }
}