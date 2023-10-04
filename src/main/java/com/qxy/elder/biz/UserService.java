package com.qxy.elder.biz;

import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.LoginDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.UserSaveDto;
import com.qxy.elder.dao.querydsl.pos.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static com.qxy.elder.dao.querydsl.QUser.user;

@Service
public class UserService {
    @Autowired
    private SQLQueryFactory mqf;

    @Transactional(rollbackFor = Exception.class)
    public Long register(UserSaveDto userSaveDto) {
        Assert.isTrue(StringUtils.hasText(userSaveDto.getUsername()), "用户名不可为空");
        Assert.isTrue(StringUtils.hasText(userSaveDto.getName()), "昵称不可为空");
        Assert.isTrue(StringUtils.hasText(userSaveDto.getPassword()), "密码不可为空");
        UserPo userPo = getUserPoByUsername(userSaveDto.getUsername());
        Assert.isNull(userPo, "该用户名已存在" + userSaveDto.getUsername());
        return mqf.insert(user)
                .populate(userSaveDto)
                .executeWithKey(user.id);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto login(LoginDto loginDto){
        Assert.isTrue(StringUtils.hasText(loginDto.getUsername()), "用户名不可为空");
        Assert.isTrue(StringUtils.hasText(loginDto.getPassword()), "密码不可为空");
        UserPo userPo = getUserPoByUsername(loginDto.getUsername());
        if (Objects.isNull(userPo)) {
            throw new IllegalArgumentException("用户不存在");
        }
        if (!userPo.getPassword().equals(loginDto.getPassword())) {
            return null;
        }
        return UserDto.builder()
                .id(userPo.getId())
                .username(userPo.getUsername())
                .name(userPo.getName())
                .role(userPo.getRole())
                .build();
    }

    private UserPo getUserPoByUsername(String username) {
        Assert.isTrue(StringUtils.hasText(username), "用户名不可为空");
        return mqf.selectFrom(user)
                .where(user.username.eq(username))
                .where(user.isDeleted.eq(0))
                .fetchOne();
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto getUserDtoByUserId(Long id) {
        Assert.notNull(id, "用户id不可为空");
        UserPo userPo = mqf.selectFrom(user)
                .where(user.id.eq(id))
                .where(user.isDeleted.eq(0))
                .fetchOne();
        return UserDto.builder()
                .id(userPo.getId())
                .username(userPo.getUsername())
                .name(userPo.getName())
                .timeCoin(userPo.getTimeCoin())
                .role(userPo.getRole())
                .ctime(userPo.getCtime())
                .mtime(userPo.getMtime())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public void costTimeCoin(Long timeCoin, Long userId) {
        Assert.notNull(timeCoin, "时间币额不可为空");
        Assert.isTrue(timeCoin > 0, "时间额不可小于0");
        UserDto userDto = getUserDtoByUserId(userId);
        Assert.notNull(userDto, "用户不存在");
        Assert.isTrue(userDto.getTimeCoin() > timeCoin, "当前余额已不足"  + timeCoin + "支付时间币失败");
        long value = userDto.getTimeCoin() - timeCoin;        updateTimeCoin(timeCoin, userId);
        updateTimeCoin(value, userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void earnTimeCoin(Long timeCoin, Long userId) {
        Assert.notNull(timeCoin, "时间币额不可为空");
        Assert.isTrue(timeCoin > 0, "时间额不可小于0");
        UserDto userDto = getUserDtoByUserId(userId);
        Assert.notNull(userDto, "用户不存在");
        long value = userDto.getTimeCoin() + timeCoin;
        updateTimeCoin(value, userId);
    }

    private void updateTimeCoin(Long timeCoin, Long userId) {
        mqf.update(user)
                .where(user.id.eq(userId))
                .where(user.isDeleted.eq(0))
                .set(user.timeCoin, timeCoin)
                .execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferTimeCoin(Long timeCoin, Long fromUserId, Long toUserId) {
        Assert.notNull(timeCoin, "转增时间币额不可为空");
        Assert.isTrue(timeCoin > 0, "转增时间币额必须大于0");
        Assert.notNull(fromUserId, "转增发起用户id不可为空");
        Assert.notNull(toUserId, "转增接受id不可为空");
        costTimeCoin(timeCoin, fromUserId);
        earnTimeCoin(timeCoin, toUserId);
    }
}
