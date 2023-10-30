package com.qxy.elder.portal.controller;


import com.qxy.elder.api.dto.LoginDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.UserSaveDto;
import com.qxy.elder.biz.UserService;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.*;
import com.qxy.elder.portal.exception.UserLoginException;
import com.qxy.elder.portal.util.MappingUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@Slf4j
@Api(tags = "用户管理")
@RequestMapping(value = "/web_api/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVo<Long> register(@RequestBody UserSaveVo vo) {
        UserSaveDto userSaveDto = MappingUtil.convertUserSaveVo2Dto(vo);
        Long userId = userService.register(userSaveDto);
        return ResponseVo.success(userId);
    }


    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo<UserInfoVo> login(@RequestBody LoginVo vo,
                                  @ApiIgnore HttpSession session) {
        LoginDto loginDto = MappingUtil.convertLoginVo2Dto(vo);
        UserDto userDto = userService.login(loginDto);
        if (Objects.isNull(userDto)) {
            throw new UserLoginException();
        }
        UserInfoVo userInfoVo = MappingUtil.convertUserDto2Vo(userDto);
        session.setAttribute(ElderConstants.CURRENT_USER, userInfoVo.getId());
        return ResponseVo.success(userInfoVo);
    }


    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseVo<UserInfoVo> userInfo(@ApiIgnore HttpSession session) {
        log.info("/user sessionId={}",session.getId());
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        UserDto userDto = userService.getUserDtoByUserId(userId);
        UserInfoVo user = MappingUtil.convertUserDto2Vo(userDto);
        return ResponseVo.success(user);
    }

    @ApiOperation(value = "用户登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseVo<Long> logout(@ApiIgnore HttpSession session) {
        log.info("/logout sessionId={}",session.getId());
        session.removeAttribute(ElderConstants.CURRENT_USER);
        return ResponseVo.success();
    }

    @ApiOperation(value = "时间币转账")
    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public ResponseVo<Object> transferTimeCoin(@RequestBody TimeCoinTransferVo transferVo,
                                               @ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        userService.transferTimeCoin(transferVo.getTimeCoin(), userId, transferVo.getToUserId());
        return ResponseVo.success();
    }

}
