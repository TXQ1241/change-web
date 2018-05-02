package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.User;
import net.sycu.lxp.vo.UserVo;

@Repository("userMapper")
public interface UserMapper {

    int insert(User user);

    List<User> getUser (User user);
    
    List<User> getUsers (UserVo user);

    int updateByUserSelective(User user);

	Integer getUserCount(UserVo userVo);

	void deleteUsersByIds(String[] userIds);

	List<User> getUsersByIds(String[] updateUserIds);

}