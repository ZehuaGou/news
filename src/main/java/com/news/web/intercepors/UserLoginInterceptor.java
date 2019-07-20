package com.news.web.intercepors;

import com.news.web.model.AdminInfo;
import com.news.web.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Paule Lee
 */
@Component
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 这个方法是在访问接口之前执行的，
     * 我们只需要在这里写验证登陆状态的业务逻辑，
     * 就可以在用户调用指定接口之前验证登陆状态了
     * 在控制器执行前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        // 每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        // 这里的User是登陆时放入session的
        UserInfo existUser = (UserInfo) session.getAttribute("existUser");
        AdminInfo existAdmin = (AdminInfo) session.getAttribute("existAdmin");

        // 如果session中没有user，表示没登陆
        if (existUser != null || existAdmin != null) {
            // 这个方法返回false表示忽略当前请求
            // 如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
            //通过拦截器，继续执行请求
            return true;
        } else {
            try {
                response.sendRedirect(request.getContextPath() + "/user/jump2login");
            } catch (Exception e) {
                log.error("有异常");
                e.printStackTrace();
            }

            //没有通过拦截器，返回登录页面
            return false;
        }
    }

    /**
     * 在后端控制器执行后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 整个请求执行完成后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
    }
}