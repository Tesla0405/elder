package com.qxy.elder.biz;

import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.*;
import com.qxy.elder.dao.querydsl.pos.MissionPo;
import com.qxy.elder.enums.MissionStatusEnum;
import com.qxy.elder.enums.UserRoleEnum;
import com.qxy.elder.enums.VolunteerTagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.qxy.elder.dao.querydsl.QMission.mission;

@Service
@Slf4j
public class MissionService {

    @Resource
    private SQLQueryFactory mqf;

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public Long saveMission(MissionSaveDto saveDto) {
        Assert.notNull(saveDto.getPublishUserId(), "任务发布者id不可为空");
        Assert.hasText(saveDto.getName(), "任务名称不可为空");
        Assert.hasText(saveDto.getContent(), "任务内容不可为空");
        Assert.hasText(saveDto.getTags(), "任务标签不可为空");
        Assert.notNull(saveDto.getTimeCoinPrice(), "任务时间币价格不可为空");
        Assert.isTrue(saveDto.getTimeCoinPrice() > 0, "任务时间币价格必须大于0");
        if (StringUtils.hasText(saveDto.getTags())) {
            String[] tagArr = saveDto.getTags().split(",");
            Arrays.asList(tagArr).forEach(VolunteerTagEnum::getByValue);
        }

        UserDto userDto = userService.getUserDtoByUserId(saveDto.getPublishUserId());
        Assert.isTrue(UserRoleEnum.ELDER.getCode().equals(userDto.getRole()),
                "当前角色不是老年人,不允许保存任务");
        if (Objects.nonNull(saveDto.getId())) {
            MissionInfoDto missionInfoDto = getById(saveDto.getId());
            Assert.notNull(missionInfoDto, "任务" + saveDto.getId() + "不存在");
            Assert.isTrue(missionInfoDto.getPublishUserId().equals(saveDto.getPublishUserId()),
                    "您不是这个任务的发布用户,不允许修改任务信息");
            mqf.update(mission)
                    .where(mission.id.eq(saveDto.getId()))
                    .populate(saveDto)
                    .execute();
            return saveDto.getId();
        } else {
            return mqf.insert(mission)
                    .populate(saveDto)
                    .executeWithKey(mission.id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public PageResult<MissionInfoDto> queryMission(QueryMissionDto queryDto) {
        Assert.notNull(queryDto, "查询任务对象不可为空");
        Assert.notNull(queryDto.getPage(), "查询分页不可为空");
        Assert.notNull(queryDto.getPageSize(), "查询分页大小不可为空");

        SQLQuery<MissionPo> countSQLQuery = generateMissionSQLQuery(queryDto),
                dataSQLQuery = generateMissionSQLQuery(queryDto);
        Long total = countSQLQuery.fetchCount();
        if (total <= 0L) {
            return PageResult.emptyPage();
        }
        List<MissionPo> data = dataSQLQuery.fetch();
        List<MissionInfoDto> missionInfoDtoList = data.stream()
                .map(this::convertMissionPo2Dto)
                .collect(Collectors.toList());
        return PageResult.<MissionInfoDto>builder()
                .total(total)
                .data(missionInfoDtoList)
                .build();

    }

    private SQLQuery<MissionPo> generateMissionSQLQuery(QueryMissionDto queryDto) {
        SQLQuery<MissionPo> sqlQuery = mqf.selectFrom(mission)
                .where(mission.isDeleted.eq(0));
        if (Objects.nonNull(queryDto.getPublishUserId())) {
            sqlQuery.where(mission.publishUserId.eq(queryDto.getPublishUserId()));
        }
        if (Objects.nonNull(queryDto.getAccomplishUserId())) {
            sqlQuery.where(mission.accomplishUserId.eq(queryDto.getAccomplishUserId()));
        }
        if (!CollectionUtils.isEmpty(queryDto.getTags())) {
            queryDto.getTags().forEach(tag -> {
                sqlQuery.where(mission.tags.like("%" + tag + "%"));
            });
        }
        sqlQuery.offset((long) (queryDto.getPage() - 1) * queryDto.getPageSize());
        sqlQuery.limit(queryDto.getPageSize());
        sqlQuery.orderBy(mission.mtime.desc());
        return sqlQuery;
    }

    @Transactional(rollbackFor = Exception.class)
    public void publishMission(Long id, Long userId) {
        MissionInfoDto missionInfoDto = getById(id);
        Assert.notNull(missionInfoDto, "任务" + id + "不存在");
        Assert.isTrue(missionInfoDto.getPublishUserId().equals(userId),
                "您不是该任务的发布用户,不允许发布该任务");
        Assert.isTrue(missionInfoDto.getMissionStatus().equals(MissionStatusEnum.WAIT_PUBLISH.getCode()),
                "当前任务已经不是待发布状态,不允许发布任务");

        userService.costTimeCoin(missionInfoDto.getTimeCoinPrice(), userId);
        mqf.update(mission)
                .set(mission.missionStatus, MissionStatusEnum.WAIT_PUBLISH.getCode())
                .where(mission.id.eq(id))
                .execute();
    }


    @Transactional(rollbackFor = Exception.class)
    public void acceptMission(Long id, Long userId) {
        MissionInfoDto missionInfoDto = getById(id);
        Assert.notNull(missionInfoDto, "任务" + id + "不存在");
        Assert.isTrue(missionInfoDto.getMissionStatus().equals(MissionStatusEnum.WAIT_ACCEPT.getCode()),
                "任务已经不是待接取状态,接取任务失败");
        UserDto userDto = userService.getUserDtoByUserId(userId);
        Assert.isTrue(UserRoleEnum.VOLUNTEER.getCode().equals(userDto.getRole()),
                "当前角色不是志愿者,不允许接受任务");

        mqf.update(mission)
                .set(mission.missionStatus, MissionStatusEnum.WAIT_COMPLETE.getCode())
                .set(mission.accomplishUserId, userId)
                .where(mission.id.eq(id))
                .execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public void accomplishMission(Long id, Long userId) {
        MissionInfoDto missionInfoDto = getById(id);
        Assert.notNull(missionInfoDto, "任务" + id + "不存在");
        Assert.isTrue(missionInfoDto.getMissionStatus().equals(MissionStatusEnum.WAIT_COMPLETE.getCode()),
                "任务已经不是待完成状态,接取任务失败");
        Assert.isTrue(missionInfoDto.getAccomplishUserId().equals(userId),
                "您不是接取该任务的用户,完成任务失败");

        userService.earnTimeCoin(missionInfoDto.getTimeCoinPrice(), userId);
        mqf.update(mission)
                .set(mission.missionStatus, MissionStatusEnum.ACCOMPLISHED.getCode())
                .where(mission.id.eq(id))
                .execute();

    }

    public void abandonMission(Long id, Long userId) {
        MissionInfoDto missionInfoDto = getById(id);
        Assert.notNull(missionInfoDto, "任务" + id + "不存在");
        Assert.isTrue(missionInfoDto.getPublishUserId().equals(userId),
                "您不是该任务的发布用户,不允许废弃该任务");

        mqf.update(mission)
                .set(mission.missionStatus, MissionStatusEnum.INVALID.getCode())
                .where(mission.id.eq(id))
                .execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public MissionInfoDto getById(Long id) {
        MissionPo missionPo = mqf.selectFrom(mission)
                .where(mission.id.eq(id))
                .where(mission.isDeleted.eq(0))
                .fetchOne();
        return convertMissionPo2Dto(missionPo);
    }

    private MissionInfoDto convertMissionPo2Dto(MissionPo po) {
        if (Objects.isNull(po)) {
            return null;
        }
        return MissionInfoDto.builder()
                .id(po.getId())
                .name(po.getName())
                .content(po.getContent())
                .publishUserId(po.getPublishUserId())
                .accomplishUserId(po.getAccomplishUserId())
                .tags(po.getTags())
                .timeCoinPrice(po.getTimeCoinPrice())
                .missionStatus(po.getMissionStatus())
                .missionStatusDesc(MissionStatusEnum.getByCode(po.getMissionStatus()).getDesc())
                .build();
    }

}
