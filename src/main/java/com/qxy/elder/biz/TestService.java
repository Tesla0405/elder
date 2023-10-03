package com.qxy.elder.biz;

import com.querydsl.sql.SQLQueryFactory;
import com.qxy.elder.api.ITestService;
import com.qxy.elder.dao.querydsl.pos.ElderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.qxy.elder.dao.querydsl.QElder.elder;

@Service
public class TestService implements ITestService {

    @Autowired
    private SQLQueryFactory mqf;

    @Transactional(rollbackFor = Exception.class)
    public void testConnection(String name, String idCard) {
        ElderPo po = new ElderPo();
        po.setName(name);
        po.setIdCard(idCard);
        String s = mqf.from(elder)
                .select(elder.name)
                .where(elder.id.eq(1L)).fetchFirst();
        mqf.insert(elder)
                .populate(po)
                .execute();
//        throw new RuntimeException("错误");
    }
}
