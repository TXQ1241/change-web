package net.sycu.lxp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.UserMapper;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.vo.UserVo;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
    @Qualifier("userMapper")
    UserMapper userMapper;

    public List<User> getUsers(UserVo userVo) {
        return userMapper.getUsers(userVo);
    }

	public List<User> getUser(User user) {
		return userMapper.getUser(user);
	}

	public void update(User user) {
		userMapper.updateByUserSelective(user);
	}

	public void save(User user) {
		userMapper.insert(user);
	}

	public Integer getUserCount(UserVo userVo) {

		return userMapper.getUserCount(userVo);
	}

	public void deleteUsersByIds(String[] userIds) {
		userMapper.deleteUsersByIds(userIds);
	}
	
	public List<User> getUsersByIds(String[] updateUserIds) {
		return userMapper.getUsersByIds(updateUserIds);
	}
	
}
