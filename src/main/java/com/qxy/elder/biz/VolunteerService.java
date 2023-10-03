package com.qxy.elder.biz;

import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.ElderInfoDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.VolunteerInfoDto;
import com.qxy.elder.api.dto.VolunteerInfoSaveDto;
import com.qxy.elder.dao.querydsl.pos.ElderPo;
import com.qxy.elder.dao.querydsl.pos.VolunteerPo;
import com.qxy.elder.enums.UserRoleEnum;
import com.qxy.elder.enums.VolunteerTagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.qxy.elder.dao.querydsl.QElder.elder;
import static com.qxy.elder.dao.querydsl.QVolunteer.volunteer;

@Service
@Slf4j
public class VolunteerService {

    @Autowired
    private SQLQueryFactory mqf;

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public void completeInfo(VolunteerInfoSaveDto saveDto) {
        Assert.notNull(saveDto, "补充信息不可为空");
        Assert.notNull(saveDto.getUserId(), "用户id不可为空");
        Assert.hasText(saveDto.getName(), "志愿者姓名不可为空");
        Assert.notNull(saveDto.getSex(), "志愿者性别不可为空");
        Assert.notNull(saveDto.getBirth(), "志愿者生日不可为空");
        Assert.hasText(saveDto.getIdCard(), "志愿者身份证号码不可为空");
        if (!StringUtils.hasText(saveDto.getTags())) {
            String[] tagArr = saveDto.getTags().split(",");
            Arrays.asList(tagArr).forEach(VolunteerTagEnum::getByValue);
        }

        UserDto userDto = userService.getUserDtoByUserId(saveDto.getUserId());
        Assert.notNull(userDto, "用户" + saveDto.getUserId() + "不存在,补充志愿者信息失败");
        Assert.isTrue(userDto.getRole().equals(UserRoleEnum.VOLUNTEER.getCode()), "用户角色不为志愿者,不允许补充志愿者信息");
        List<Long> currentElderIds = mqf.select(volunteer.id)
                .from(volunteer)
                .where(volunteer.userId.eq(saveDto.getUserId()))
                .where(volunteer.isDeleted.eq(0))
                .fetch();
        if (!CollectionUtils.isEmpty(currentElderIds)) {
            mqf.update(volunteer)
                    .where(volunteer.id.eq(currentElderIds.get(0)))
                    .where(volunteer.isDeleted.eq(0))
                    .populate(saveDto)
                    .execute();
        } else {
            mqf.insert(volunteer)
                    .populate(saveDto)
                    .execute();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public VolunteerInfoDto getInfoByUserId(Long userId) {
        List<VolunteerPo> resultList = mqf.selectFrom(volunteer)
                .where(volunteer.userId.eq(userId))
                .where(volunteer.isDeleted.eq(0))
                .fetch();
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }
        return convertPo2Dto(resultList.get(0));
    }

    private VolunteerInfoDto convertPo2Dto(VolunteerPo po) {
        if (Objects.isNull(po)) {
            return null;
        }
        return VolunteerInfoDto.builder()
                .id(po.getId())
                .name(po.getName())
                .sex(po.getSex())
                .birth(new Timestamp(po.getBirth().getTime()))
                .idCard(po.getIdCard())
                .ctime(po.getCtime())
                .mtime(po.getMtime())
                .build();
    }

}
