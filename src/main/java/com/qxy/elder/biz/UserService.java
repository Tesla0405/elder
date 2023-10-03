package com.qxy.elder.biz;

import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.dto.LoginDto;
import com.qxy.elder.api.dto.UserDto;
import com.qxy.elder.api.dto.UserSaveDto;
import com.qxy.elder.dao.querydsl.pos.ElderPo;
import com.qxy.elder.dao.querydsl.pos.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static com.qxy.elder.dao.querydsl.QElder.elder;
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
                .build();
    }

    private UserPo getUserPoByUsername(String username) {
        Assert.isTrue(StringUtils.hasText(username), "用户名不可为空");
        return mqf.selectFrom(user)
                .where(user.username.eq(username))
                .where(user.isDeleted.eq(0))
                .fetchOne();
    }
}
