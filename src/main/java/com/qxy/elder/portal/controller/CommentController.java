package com.qxy.elder.portal.controller;

import com.qxy.elder.api.dto.CommentCreateDto;
import com.qxy.elder.api.dto.CommentInfoDto;
import com.qxy.elder.api.dto.PageResult;
import com.qxy.elder.api.dto.QueryCommentDto;
import com.qxy.elder.biz.CommentService;
import com.qxy.elder.portal.constants.ElderConstants;
import com.qxy.elder.portal.controller.vo.CommentCreateVo;
import com.qxy.elder.portal.controller.vo.CommentInfoVo;
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
@Api(tags = "评论管理")
@RequestMapping(value = "/web_api/v1/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "发布评论")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseVo<Long> createComment(@RequestBody CommentCreateVo createVo,
                                          HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        CommentCreateDto createDto = MappingUtil.convertCommentCreateDto2Vo(createVo, userId);
        commentService.createComment(createDto);
        return ResponseVo.success();
    }

    @ApiOperation(value = "删除评论")
    @RequestMapping(value = "/delete/{commentId}", method = RequestMethod.DELETE)
    public ResponseVo<Object> deleteComment(@PathVariable Long commentId,
                                            HttpSession session) {
        Long userId = (Long) session.getAttribute(ElderConstants.CURRENT_USER);
        commentService.deleteComment(commentId, userId);
        return ResponseVo.success();
    }


    @ApiOperation(value = "查询评论")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseVo<PageResult<CommentInfoVo>> queryComment(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam(value = "mission_id", required = false) Long missionId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @ApiIgnore HttpSession session) {
        QueryCommentDto queryCommentDto = QueryCommentDto.builder()
                .userId(userId)
                .missionId(missionId)
                .page(page)
                .pageSize(pageSize)
                .build();
        PageResult<CommentInfoDto> pageResult = commentService.queryComment(queryCommentDto);
        List<CommentInfoVo> resultVoList = pageResult.getData().stream()
                .map(MappingUtil::convertCommentInfoDto2Vo)
                .collect(Collectors.toList());
        PageResult<CommentInfoVo> result = PageResult.<CommentInfoVo>builder()
                .total(pageResult.getTotal())
                .data(resultVoList)
                .build();
        return ResponseVo.success(result);
    }
}
