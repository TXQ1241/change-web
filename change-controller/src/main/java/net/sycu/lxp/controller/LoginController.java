package net.sycu.lxp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IOperateLogService;
import net.sycu.lxp.service.IUserService;

@Controller
@RequestMapping("/login/")
public class LoginController{

    @Autowired
    @Qualifier("userService")
    IUserService userService;
    
    @Autowired
    @Qualifier("operateService")
    IOperateLogService operateService;

    /**
     * 提交测试
     *
     * @return
     * @throws
     * @Description: 跳转到登录页面
     * @author xyh
     * @Date 17:21 2018/2/23
     * @method handlerLogin
     * params
     **/
    @RequestMapping("loginHandler")
    public String loginHandler() {

        return "login";
    }

    /**
     *  @Description:
     *  @author huangy
     *  @Date 2018/4/12
     *  @method executeLogin
     *  params  [User]用户对象，帐号，密码
     *  @return Map 用户登录信息
     *  @exception
     **/
    @RequestMapping("login")
    @ResponseBody
    public Map<String, String> executeLogin(HttpServletRequest request,@RequestBody User user) {
        //登录状态
        String status;
        //登录信息
        String message;
        Map<String, String> msgMap = new HashMap<String, String>();
        User userInfo = null;
        try{
            List<User> userList = userService.getUser(user);
            if(userList!=null && userList.size() > 0) {
                userInfo = userList.get(0);
            }
            if (userInfo != null) {
                if (user.getPassword().equals(userInfo.getPassword())){
                    status = Constant.AjaxStatus.AJAX_SUCCESS;
                    message = "登录成功";
                    //登陆成功后保存登陆日志信息
                    operateService.saveLog(userInfo, Constant.OperateConstants.OPERATE_LOGIN);
                    //更新用户的登录时间
                    userService.update(userInfo);
                    //将用户信息放入session域中
                    request.getSession().setAttribute(Constant.CURRENT_USER, userInfo);
                    msgMap.put("userType", userInfo.getUserType());
                } else {
                    status = Constant.AjaxStatus.AJAX_FAIL;
                    message = "密码错误";
                }
            } else {
                status = Constant.AjaxStatus.AJAX_FAIL;
                message = "当前用户不存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = Constant.AjaxStatus.AJAX_FAIL;
            message = "登录异常，请联系管理员";
        }

        msgMap.put("status",status);
        msgMap.put("msg",message);

        return msgMap;

    }

    /**
     *  @Description: 注销/退出
     *  @author huangy
     *  @Date 2018/4/13
     *  @method logout
     *  params  [request]
     *  @return String 登录页面
     *  @exception
     **/
    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //将session中用户的登录信息设置为空
        request.getSession().setAttribute(Constant.CURRENT_USER,null);
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"
                +request.getServerPort()+path+"/login.html";
        try {
            response.sendRedirect(basePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  @Description: 跳转到主页
     *  @author huangy
     *  @Date 2018/4/13
     *  @method goToIndex
     *  params  []
     *  @return String
     *  @exception
     **/
    @RequestMapping("home")
    public String goToIndex() {
        return "index";
    }
    
    @RequestMapping("loginStatus")
    @ResponseBody
    public Map<String, String> getLoginStatus (HttpServletRequest request) {
    	Map<String, String> msgMap = new HashMap<String, String>();
    	User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
    	String userName = "";
    	if (user != null) {
    		userName = user.getUserName();
    	}
    	msgMap.put("userName", userName);
    	return msgMap;
    }
    
}

