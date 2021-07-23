package cn.seecoder.courselearning.po.user;

import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.vo.user.UserVO;
import cn.seecoder.courselearning.vo.vip.VipChargeVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Date;

@Getter
@Setter
public class User {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private String picture;

    private Integer balance;

    private UserRole userRole;

    private Date createTime;

    // 0代表不是vip，1代表是vip
    private int isVip;

    // vip到期时间
    private Date vipEndTime;

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public User() {
    }

    public User(Integer id, String uname, String phone, String password, String picture, Integer balance, UserRole userRole, Date createTime, int isVip, Date vipEndTime) {
        this.id = id;
        this.uname = uname;
        this.phone = phone;
        this.password = password;
        this.picture = picture;
        this.balance = balance;
        this.userRole = userRole;
        this.createTime = createTime;
        this.isVip = isVip;
        this.vipEndTime = vipEndTime;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }


    public User(UserVO userVO){
        id = userVO.getId();
        uname = userVO.getUname();
        phone = userVO.getPhone();
        password = userVO.getPassword();
        picture = userVO.getPicture();
        balance = userVO.getBalance();
        userRole = userVO.getUserRole();
        createTime = userVO.getCreateTime();
        isVip=userVO.getIsVip();
        vipEndTime=userVO.getVipEndTime();
    }

    public void becomeVip() {
        this.isVip = 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", picture='" + picture + '\'' +
                ", balance=" + balance +
                ", userRole=" + userRole +
                ", createTime=" + createTime +
                ", isVip=" + isVip +
                ", vipEndTime=" + vipEndTime +
                '}';
    }
}