package net.sycu.lxp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IUserService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.UserVo;

/**
 * @author lxp
 **/
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    @Qualifier("userService")
    IUserService userService;

    @RequestMapping("view")
    public String toUserList(String userType){
        String page = "admin";
        if (userType != null && Constant.UserConstants.GENERAL_USER.
                equals(userType)) {
            page = "systemUser";
        } else if(userType != null && Constant.UserConstants.GOODS_PAGE.equals(userType)) {
        	page = "goodsEdit";
        } else if(userType != null && Constant.UserConstants.OPERATE_PAGE_LOGIN.equals(userType)) {
        	page = "loginLog";
        } else if(userType != null && Constant.UserConstants.OPERATE_PAGE_CHANGE.equals(userType)) {
        	page = "passwordChangeLog";        			
        }
        return page;
    }


    /**
     *  @Description: 通过用户对象获取用户数据
     *  @author lxp
     *  @method getUsers
     *  params  [user] 用户对象
     *  @return DataVo 用户集对象
     *  @exception
     **/
    @RequestMapping("userList")
    @ResponseBody
    public DataVo getUsers(UserVo userVo) {
        DataVo dataVo = new DataVo();
        Integer pageNum = userVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
            userVo.setPageNum((pageNum-1)*userVo.getPageSize());
        }
        try {
            List<User> userList = userService.getUsers(userVo);
            dataVo.setDatalist(userList);
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
            if (userList != null) {
                dataVo.setCount(userService.getUserCount(userVo));
            } else {
                dataVo.setCount(Constant.ZERO_NUM);
            }
        } catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
            e.printStackTrace();
        }
        return dataVo;
    }

    /**
     *  @Description: 获取当前用户信息
     *  @author lxp
     *  @method getUserInfo
     *  params  []
     *  @return User
     *  @exception
     **/
    @RequestMapping("getUserInfo")
    @ResponseBody
    public User getUserInfo (HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        List<User> userList = userService.getUser(user);
        if(userList != null && userList.size() > 0) {
        	user = userList.get(0);
        }
        return user;
    }

    /**
     *  @Description: 保存用户（系统新增，注册新增）
     *  @author lxp
     *  @method saveUsers
     *  params  [user] 用户对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> saveUser(@RequestBody User user) {
        Map<String, String> msgMap = new HashMap<String, String>();
        try {
            if (StringUtils.isNotBlank(user.getId())) {
                userService.update(user);
            } else {
                user.setId(StringUtils.getUUID());
                user.setCreateTime(new Date());
                user.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
                userService.save(user);
            }
            msgMap.put("userType", user.getUserType());
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存用户信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存用户信息失败");
        }
        return msgMap;
    }

    /**
     *  @Description: 删除用户
     *  @author lxp
     *  @method deleteUsers
     *  params  [user] 用户对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteUsers(@RequestBody User user) {
        Map<String, String> msgMap = new HashMap<String, String>();
        String [] userIds = null;
        if(StringUtils.isNotBlank(user.getIds())){
            userIds = user.getIds().split(",");
        }
        try {
            if(userIds != null && userIds.length > 0) {
                userService.deleteUsersByIds(userIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除用户信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除用户信息失败");
        }
        return msgMap;
    }
    
    /**
     *  @Description: 修改密码
     *  @author lxp
     *  @method changePassword
     *  params  [user] 用户对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("changePassword")
    @ResponseBody
    public Map<String, String> changePassword(HttpServletRequest request, @RequestBody User user) {   	
    	Map<String, String> msgMap = new HashMap<String, String>();
    	String status = Constant.AjaxStatus.AJAX_SUCCESS;
    	String msg = "修改密码成功";
    	User userInfo = new User();
    	try {
	    	List<User> userList = userService.getUser(user);		
	    	if (userList != null && userList.size()>0) {
	    		userInfo = userList.get(0);
	        	userInfo.setPassword(user.getPassword());
	        	userService.update(userInfo);
	        	request.getSession().setAttribute(Constant.CURRENT_USER, null);
	    	}else {
	    		status = Constant.AjaxStatus.AJAX_FAIL;
	    		msg = "当前用户不存在，请联系管理员";
	    	}
    	} catch (Exception e) {
    		status = Constant.AjaxStatus.AJAX_FAIL;
    		msg = "修改密码异常，请联系管理员";
    	}
    	msgMap.put(status, msg);
    	return msgMap;    	
    }

}
