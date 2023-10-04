package com.qxy.elder.biz;

import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.ElderInfoDto;
import com.qxy.elder.api.dto.ElderInfoSaveDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.dao.querydsl.pos.ElderPo;
import com.qxy.elder.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static com.qxy.elder.dao.querydsl.QElder.elder;

@Service
public class ElderService {

    @Autowired
    private SQLQueryFactory mqf;

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public void completeInfo(ElderInfoSaveDto saveDto) {
        Assert.notNull(saveDto, "补充信息不可为空");
        Assert.notNull(saveDto.getUserId(), "用户id不可为空");
        Assert.hasText(saveDto.getName(), "老人姓名不可为空");
        Assert.notNull(saveDto.getSex(), "老人性别不可为空");
        Assert.notNull(saveDto.getBirth(), "老人生日不可为空");
        Assert.hasText(saveDto.getIdCard(), "老人身份证号码不可为空");
        UserDto userDto = userService.getUserDtoByUserId(saveDto.getUserId());
        Assert.notNull(userDto, "用户" + saveDto.getUserId() + "不存在,补充老人信息失败");
        Assert.isTrue(userDto.getRole().equals(UserRoleEnum.ELDER.getCode()), "用户角色不为老人,不允许补充老人信息");
        List<Long> currentElderIds = mqf.select(elder.id)
                .from(elder)
                .where(elder.userId.eq(saveDto.getUserId()))
                .where(elder.isDeleted.eq(0))
                .fetch();
        if (!CollectionUtils.isEmpty(currentElderIds)) {
            mqf.update(elder)
                    .where(elder.id.eq(currentElderIds.get(0)))
                    .where(elder.isDeleted.eq(0))
                    .populate(saveDto)
                    .execute();
        } else {
            mqf.insert(elder)
                    .populate(saveDto)
                    .execute();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ElderInfoDto getInfoByUserId(Long userId) {
        List<ElderPo> resultList = mqf.selectFrom(elder)
                .where(elder.userId.eq(userId))
                .where(elder.isDeleted.eq(0))
                .fetch();
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }
        return convertPo2Dto(resultList.get(0));
    }

    private ElderInfoDto convertPo2Dto(ElderPo po) {
        if (Objects.isNull(po)) {
            return null;
        }
        return ElderInfoDto.builder()
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
