package cn.seecoder.courselearning.mapperservice.user;

import cn.seecoder.courselearning.po.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

//Unit Test Has Finished
@Repository("UserMapper")
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);



    List<User> selectAll();

    // 根据用户id更新用户账号信息
    int updateByPrimaryKey(User record);

    void increaseBalance(@Param(value = "id") Integer id, @Param(value = "delta")Integer delta);

    void decreaseBalance(@Param(value = "id") Integer id, @Param(value = "delta")Integer delta);

    User selectByPhone(String phone);
}