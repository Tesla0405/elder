package com.qxy.elder.portal.controller;

import com.qxy.elder.api.dto.MissionInfoDto;
import com.qxy.elder.api.dto.MissionSaveDto;
import com.qxy.elder.api.dto.PageResult;
import com.qxy.elder.api.dto.QueryMissionDto;
import com.qxy.elder.biz.MissionService;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.MissionInfoVo;
import com.qxy.elder.portal.controller.vo.MissionSaveVo;
import com.qxy.elder.portal.controller.vo.ResponseVo;
import com.qxy.elder.portal.util.MappingUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "任务管理")
@RequestMapping(value = "/web_api/v1/mission")
public class MissionController {

    @Resource
    private MissionService missionService;

    @ApiOperation(value = "保存任务")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseVo<Long> saveMission(@RequestBody MissionSaveVo saveVo,
                                         @ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        MissionSaveDto saveDto = MappingUtil.convertMissionSaveVo2Dto(saveVo, userId);
        Long missionId = missionService.saveMission(saveDto);
        return ResponseVo.success(missionId);
    }

    @ApiOperation(value = "查询任务")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseVo<PageResult<MissionInfoVo>> queryMission(
            @RequestParam(value = "is_publish_user", defaultValue = "0") Integer isPublishUser,
            @RequestParam(value = "is_accomplish_user", defaultValue = "0") Integer isAccomplishUser,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @ApiIgnore HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        QueryMissionDto queryDto = QueryMissionDto.builder()
                .publishUserId(isPublishUser > 0 ? userId : null)
                .accomplishUserId(isAccomplishUser > 0 ? userId : null)
                .tags(tags)
                .page(page)
                .pageSize(pageSize)
                .build();
        PageResult<MissionInfoDto> pageResult = missionService.queryMission(queryDto);
        List<MissionInfoVo> resultVoList = pageResult.getData().stream()
                .map(MappingUtil::convertMissionInfoDto2Vo)
                .collect(Collectors.toList());
        PageResult<MissionInfoVo> result = PageResult.<MissionInfoVo>builder()
                .data(resultVoList)
                .total(pageResult.getTotal())
                .build();
        return ResponseVo.success(result);
    }

    @ApiOperation(value = "发布任务")
    @RequestMapping(value = "/publish/{missionId}", method = RequestMethod.PUT)
    public ResponseVo<Object> publishMission(@PathVariable Long missionId,
                                             HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        missionService.publishMission(missionId, userId);
        return ResponseVo.success();
    }

    @ApiOperation(value = "接受任务")
    @RequestMapping(value = "/accept/{missionId}", method = RequestMethod.PUT)
    public ResponseVo<Object> acceptMission(@PathVariable Long missionId,
                                             HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        missionService.acceptMission(missionId, userId);
        return ResponseVo.success();
    }

    @ApiOperation(value = "完成任务")
    @RequestMapping(value = "/accomplish/{missionId}", method = RequestMethod.PUT)
    public ResponseVo<Object> accomplishMission(@PathVariable Long missionId,
                                             HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        missionService.accomplishMission(missionId, userId);
        return ResponseVo.success();
    }

    @ApiOperation(value = "废弃任务")
    @RequestMapping(value = "/abandon/{missionId}", method = RequestMethod.PUT)
    public ResponseVo<Object> abandonMission(@PathVariable Long missionId,
                                             HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        missionService.abandonMission(missionId, userId);
        return ResponseVo.success();
    }
}
