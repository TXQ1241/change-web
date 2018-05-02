package net.sycu.lxp.base;

import java.util.Date;

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
import net.sycu.lxp.utils.StringUtils;

public class ChangInterception implements HandlerInterceptor {
	
	@Autowired
	@Qualifier("operateService")
	IOperateLogService operateService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		
		if (user != null) {		
			OperateLog log = new OperateLog();
			log.setId(StringUtils.getUUID());
			log.setOperateTime(new Date());
			log.setUserAccount(user.getAccount());
			log.setUserName(user.getUserName());
			log.setOperateType(Constant.OperateConstants.OPERATE_CHANGE_PSW);
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
