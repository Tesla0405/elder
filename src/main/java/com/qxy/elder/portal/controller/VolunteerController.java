package com.qxy.elder.portal.controller;

import com.qxy.elder.api.dto.VolunteerInfoDto;
import com.qxy.elder.api.dto.VolunteerInfoSaveDto;
import com.qxy.elder.biz.VolunteerService;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.*;
import com.qxy.elder.portal.util.MappingUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@Api(tags = "志愿者管理")
@RequestMapping(value = "/web_api/v1/volunteer")
public class VolunteerController {

    @Resource
    private VolunteerService volunteerService;

    @ApiOperation(value = "完善信息")
    @RequestMapping(value = "/complete/info", method = RequestMethod.POST)
    public void completeInfo(@RequestBody VolunteerInfoSaveVo saveVo,
                             @ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        saveVo.setUserId(userId);
        VolunteerInfoSaveDto saveDto = MappingUtil.convertVolunteerInfoSaveVo2Dto(saveVo);
        volunteerService.completeInfo(saveDto);
    }

    @ApiOperation(value = "获取完整信息")
    @RequestMapping(value = "/complete/info", method = RequestMethod.GET)
    public ResponseVo<VolunteerInfoVo> getCompleteInfo(@ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        VolunteerInfoDto infoDto = volunteerService.getInfoByUserId(userId);
        VolunteerInfoVo resultVo = MappingUtil.convertVolunteerInfoDto2Vo(infoDto);
        return ResponseVo.success(resultVo);
    }

}
