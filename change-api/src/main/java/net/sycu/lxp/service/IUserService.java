package net.sycu.lxp.service;

import java.util.List;

import net.sycu.lxp.pojo.User;
import net.sycu.lxp.vo.UserVo;

public interface IUserService {
	/**
     *  @Description: 通过用户对象获取用户
     *  @author lxp
     *  params  [userVo] 用户查询对象
     *  @return 用户集合
     *  @exception
     **/
    List<User> getUsers(UserVo userVo);
    
    /**
     *  @Description: 通过 user对象获取用户
     *  @author lxp
     *  params  [userVo] 用户对象
     *  @return List<User>用户集合
     *  @exception
     **/
    List<User> getUser(User user);
    
    /**
     *  @Description: 更新用户
     *  @author lxp
     *  params  [user] 用户对象
     *  @exception
     **/
	void update(User user);
	
    /**
     *  @Description: 保存用户
     *  @author lxp
     *  params  [user] 用户对象
     *  @exception
     **/
	void save(User user);
	
    /**
     *  @Description: 获取用户记录数
     *  @author lxp
     *  params  [userVo] 用户对象
     *  @exception
     **/
	Integer getUserCount(UserVo userVo);
	
    /**
     *  @Description:批量删除用户
     *  @author lxp
     *  params  [userIds] 用户id集合
     *  @exception
     **/
	void deleteUsersByIds(String[] userIds);
	
	/**
	 * 通过id数组获取用户信息
	 * @param updateUserIds
	 * @return
	 */
	List<User> getUsersByIds(String[] updateUserIds);
    
}
