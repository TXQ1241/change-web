package net.sycu.lxp.base;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IOperateLogService;
import net.sycu.lxp.service.IUserService;
import net.sycu.lxp.utils.StringUtils;

public class LoginInterception implements HandlerInterceptor {
	
	@Autowired
	@Qualifier("userService")
	IUserService userService;
	
	@Autowired
	@Qualifier("operateService")
	IOperateLogService operateService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String account = request.getParameter(Constant.SystemField.ACCOUNT_ATTR);
		User userInfo = new User();
		userInfo.setAccount(account);
		List<User> userList = userService.getUser(userInfo);
		if(userList != null && userList.size() > 0) {
			userInfo = userList.get(0);
			OperateLog log = new OperateLog();
			log.setId(StringUtils.getUUID());
			log.setOperateTime(new Date());
			log.setUserAccount(userInfo.getAccount());
			log.setUserName(userInfo.getUserName());
			log.setOperateType(Constant.OperateConstants.OPERATE_LOGIN);
			operateService.save(log);
		} else {
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
