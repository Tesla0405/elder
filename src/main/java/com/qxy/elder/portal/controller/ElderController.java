package com.qxy.elder.portal.controller;

import com.qxy.elder.api.dto.ElderInfoDto;
import com.qxy.elder.api.dto.ElderInfoSaveDto;
import com.qxy.elder.biz.ElderService;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.ElderInfoSaveVo;
import com.qxy.elder.portal.controller.vo.ElderInfoVo;
import com.qxy.elder.portal.controller.vo.ResponseVo;
import com.qxy.elder.portal.controller.vo.UserInfoVo;
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
@Api(tags = "老人管理")
@RequestMapping(value = "/web_api/v1/elder")
public class ElderController {

    @Resource
    private ElderService elderService;


    @ApiOperation(value = "完善信息")
    @RequestMapping(value = "/complete/info", method = RequestMethod.POST)
    public void completeInfo(@RequestBody ElderInfoSaveVo saveVo,
                             @ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        saveVo.setUserId(userId);
        ElderInfoSaveDto saveDto = MappingUtil.convertElderInfoSaveVo2Dto(saveVo);
        elderService.completeInfo(saveDto);
    }

    @ApiOperation(value = "获取完整信息")
    @RequestMapping(value = "/complete/info", method = RequestMethod.GET)
    public ResponseVo<ElderInfoVo> getCompleteInfo(@ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        ElderInfoDto infoDto = elderService.getInfoByUserId(userId);
        ElderInfoVo resultVo = MappingUtil.convertElderInfoDto2Vo(infoDto);
        return ResponseVo.success(resultVo);
    }
}
