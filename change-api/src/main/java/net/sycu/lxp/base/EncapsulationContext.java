package net.sycu.lxp.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.User;

/**
 * 封装BaseControllerContext的拦截器
 *
 * @author lxp
 **/
public class EncapsulationContext implements HandlerInterceptor {

    /**
     * @return
     * @throws
     * @Description: 在进入Handler方法之前执行, 判断是否登录.
     * @author lxp
     * @method params
     **/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        if (user != null && Constant.UserConstants.SYSTEM_ADMIN.equals(user.getUserType())) {
            return true;
        } else {
            //如果没有登录,就返回到登录的页面
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"
                    +request.getServerPort()+path+"/login.html";
            response.sendRedirect(basePath);
            return false;
        }


    }

    /**
     * @return
     * @throws
     * @Description: 进入Handler方法之后，返回ModelAndView之前执行
     * @author lxp
     * @method params
     **/
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         *  在进入方法之后,返回页面之前执行,这个方法一定会执行Controller里面的内容,所以在上面不能去执行,只能设置好BaseControllerContext
         *      其实就是在controller里面的方法执行完毕,在return之前执行
         */
    }

    /**
     * @return
     * @throws
     * @Description: 在执行Handler完成后执行此方法，使用于统一的异常处理
     * @author lxp
     * @method params
     **/
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**
         *  在controller的return之前执行
         *      在这里适合做一个日志的记录,记录下用户的所有操作,但是这样日志就会显得很大,不方便查看
         */
    }
}

