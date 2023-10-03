package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.ElderPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QElder is a Querydsl query type for ElderPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QElder extends com.querydsl.sql.RelationalPathBase<ElderPo> {

    private static final long serialVersionUID = -1891101313;

    public static final QElder elder = new QElder("elder");

    public final DatePath<java.sql.Date> birth = createDate("birth", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idCard = createString("idCard");

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final com.querydsl.sql.PrimaryKey<ElderPo> primary = createPrimaryKey(id);

    public QElder(String variable) {
        super(ElderPo.class, forVariable(variable), "null", "elder");
        addMetadata();
    }

    public QElder(String variable, String schema, String table) {
        super(ElderPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QElder(String variable, String schema) {
        super(ElderPo.class, forVariable(variable), schema, "elder");
        addMetadata();
    }

    public QElder(Path<? extends ElderPo> path) {
        super(path.getType(), path.getMetadata(), "null", "elder");
        addMetadata();
    }

    public QElder(PathMetadata metadata) {
        super(ElderPo.class, metadata, "null", "elder");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(birth, ColumnMetadata.named("birth").withIndex(4).ofType(Types.DATE).withSize(10).notNull());
        addMetadata(ctime, ColumnMetadata.named("ctime").withIndex(7).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(idCard, ColumnMetadata.named("id_card").withIndex(5).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(6).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(mtime, ColumnMetadata.named("mtime").withIndex(8).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(sex, ColumnMetadata.named("sex").withIndex(3).ofType(Types.TINYINT).withSize(3).notNull());
    }

}

