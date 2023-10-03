package com.qxy.elder.portal.interceptor;

import com.qxy.elder.consts.MallConst;
import com.qxy.elder.portal.controller.vo.UserInfoVo;
import com.qxy.elder.portal.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true表示继续流程，false表示中断
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("preHandle...");
        Long userId = (Long) request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(userId == null){
            log.info("userId=null");
            throw new UserLoginException();
        }
        return true;
    }
}
