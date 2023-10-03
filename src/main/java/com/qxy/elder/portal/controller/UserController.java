package com.qxy.elder.portal.controller;


import com.qxy.elder.api.dto.LoginDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.UserSaveDto;
import com.qxy.elder.biz.UserService;
import com.qxy.elder.consts.MallConst;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.LoginVo;
import com.qxy.elder.portal.controller.vo.ResponseVo;
import com.qxy.elder.portal.controller.vo.UserInfoVo;
import com.qxy.elder.portal.controller.vo.UserSaveVo;
import com.qxy.elder.portal.exception.UserLoginException;
import com.qxy.elder.portal.util.MappingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVo<Long> register(@RequestBody UserSaveVo vo) {
        UserSaveDto userSaveDto = MappingUtil.convertUserSaveVo2Dto(vo);
        Long userId = userService.register(userSaveDto);
        return ResponseVo.success(userId);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo<UserInfoVo> login(@RequestBody LoginVo vo,
                                  HttpSession session) {
        LoginDto loginDto = MappingUtil.convertLoginVo2Dto(vo);
        UserDto userDto = userService.login(loginDto);
        if (Objects.isNull(userDto)) {
            throw new UserLoginException();
        }
        UserInfoVo userInfoVo = MappingUtil.convertUserDto2Vo(userDto);
        session.setAttribute(ElderConstants.CURRENT_USER,userInfoVo);
        return ResponseVo.success(userInfoVo);
    }


    @GetMapping("/user")
    public ResponseVo<UserInfoVo> userInfo(HttpSession session){
        log.info("/user sessionId={}",session.getId());
        UserInfoVo user = (UserInfoVo) session.getAttribute(ElderConstants.CURRENT_USER);
        return ResponseVo.success(user);
    }

    @PostMapping("/logout")
    /**
     * {@link TomcatServletWebServerFactory} getSessionTimeoutInMinutes
     */
    //???
    public ResponseVo<Long> logout(HttpSession session){
        log.info("/logout sessionId={}",session.getId());
        //判断是否为登入状态

        session.removeAttribute(ElderConstants.CURRENT_USER);
        return ResponseVo.success();
    }
}
