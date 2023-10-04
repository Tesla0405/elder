package com.qxy.elder.biz;

import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.*;
import com.qxy.elder.dao.querydsl.pos.CommentPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.qxy.elder.dao.querydsl.QComment.comment;

@Service
@Slf4j
public class CommentService {

    @Resource
    private SQLQueryFactory mqf;

    @Resource
    private MissionService missionService;

    @Transactional(rollbackFor = Exception.class)
    public Long createComment(CommentCreateDto commentCreateDto) {
        Assert.notNull(commentCreateDto, "发布评论对象不可为空");
        Assert.notNull(commentCreateDto.getUserId(), "发布评论用户id不可为空");
        Assert.notNull(commentCreateDto.getMissionId(), "发布评论关联任务id不可为空");
        Assert.hasText(commentCreateDto.getContent(), "发布评论内容不可为空");
        MissionInfoDto missionInfoDto = missionService.getById(commentCreateDto.getMissionId());
        Assert.notNull(missionInfoDto, "该任务不存在,发布评论失败");
        Assert.isTrue(missionInfoDto.getPublishUserId().equals(commentCreateDto.getUserId())
                || missionInfoDto.getAccomplishUserId().equals(commentCreateDto.getUserId()),
                "仅任务相关用户可以发布评论");

        return mqf.insert(comment)
                .populate(commentCreateDto)
                .executeWithKey(comment.id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Long id, Long userId) {
        Assert.notNull(id, "删除评论id不可为空");
        Assert.notNull(userId, "删除评论用户id不可为空");
        CommentInfoDto commentInfoDto = getCommentById(id);
        Assert.notNull(commentInfoDto, "该评论不存在,删除失败");
        Assert.isTrue(commentInfoDto.getUserId().equals(userId), "您不是该评论的发布用户,不允许删除该评论");
        mqf.update(comment)
                .set(comment.isDeleted, 1)
                .where(comment.id.eq(id))
                .execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public PageResult<CommentInfoDto> queryComment(QueryCommentDto queryDto) {
        Assert.notNull(queryDto, "查询对象不可为空");
        Assert.notNull(queryDto.getPage(), "查询分页不可为空");
        Assert.notNull(queryDto.getPageSize(), "查询分页大小不可为空");
        SQLQuery<CommentPo> countSQLQuery = generateMissionSQLQuery(queryDto),
                dataSQLQuery = generateMissionSQLQuery(queryDto);
        Long total = countSQLQuery.fetchCount();
        if (total <= 0L) {
            return PageResult.emptyPage();
        }
        List<CommentPo> data = dataSQLQuery.fetch();
        List<CommentInfoDto> commentInfoDtoList = data.stream()
                .map(this::convertCommentPo2Dto)
                .collect(Collectors.toList());
        return PageResult.<CommentInfoDto>builder()
                .total(total)
                .data(commentInfoDtoList)
                .build();
    }

    private SQLQuery<CommentPo> generateMissionSQLQuery(QueryCommentDto queryDto) {
        SQLQuery<CommentPo> sqlQuery = mqf.selectFrom(comment)
                .where(comment.isDeleted.eq(0));
        if (Objects.nonNull(queryDto.getUserId())) {
            sqlQuery.where(comment.userId.eq(queryDto.getUserId()));
        }
        if (Objects.nonNull(queryDto.getMissionId())) {
            sqlQuery.where(comment.missionId.eq(queryDto.getMissionId()));
        }
        sqlQuery.offset((long) (queryDto.getPage() - 1) * queryDto.getPageSize());
        sqlQuery.limit(queryDto.getPageSize());
        sqlQuery.orderBy(comment.mtime.desc());
        return sqlQuery;
    }

    @Transactional(rollbackFor = Exception.class)
    public CommentInfoDto getCommentById(Long id) {
        CommentPo commentPo = mqf.selectFrom(comment)
                .where(comment.id.eq(id))
                .where(comment.isDeleted.eq(0))
                .fetchOne();
        return convertCommentPo2Dto(commentPo);
    }

    private CommentInfoDto convertCommentPo2Dto(CommentPo commentPo) {
        if (Objects.isNull(commentPo)) {
            return null;
        }
        return CommentInfoDto.builder()
                .id(commentPo.getId())
                .userId(commentPo.getUserId())
                .missionId(commentPo.getMissionId())
                .content(commentPo.getContent())
                .ctime(commentPo.getCtime())
                .mtime(commentPo.getMtime())
                .build();
    }

}
