package cn.seecoder.courselearning.mapperservice.user.mapperStub;

import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public class UserMapperImplStub implements UserMapper {

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        User userWithVIP = new User();
        userWithVIP.setId(1);
        userWithVIP.setBalance(10000);
        userWithVIP.setVipEndTime(new Date(System.currentTimeMillis() + 48 * 3600 * 1000));
        userWithVIP.setIsVip(1);

        User userWithNoMoney = new User();
        userWithNoMoney.setId(1);
        userWithNoMoney.setBalance(0);
        userWithNoMoney.setVipEndTime(new Date(System.currentTimeMillis() + 48 * 3600 * 1000));
        userWithNoMoney.setIsVip(1);

        User userWithNoVip = new User();
        userWithNoVip.setId(1);
        userWithNoVip.setBalance(10000);
        userWithNoVip.setIsVip(0);

        User userWithNoVipButHad = new User();
        userWithNoVipButHad.setId(1);
        userWithNoVipButHad.setBalance(10000);
        userWithNoVipButHad.setVipEndTime(new Date(System.currentTimeMillis() - 48 * 3600 * 1000));
        userWithNoVipButHad.setIsVip(0);

        User teacher = new User();
        teacher.setUserRole(UserRole.TEACHER);

        User student = new User();
        student.setUserRole(UserRole.STUDENT);
        return student;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public void increaseBalance(Integer id, Integer delta) {

    }

    @Override
    public void decreaseBalance(Integer id, Integer delta) {

    }

    @Override
    public User selectByPhone(String phone) {
        return null;
    }
}