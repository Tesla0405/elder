package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.MissionPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMission is a Querydsl query type for MissionPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMission extends com.querydsl.sql.RelationalPathBase<MissionPo> {

    private static final long serialVersionUID = 2030649089;

    public static final QMission mission = new QMission("mission");

    public final NumberPath<Long> accomplishUserId = createNumber("accomplishUserId", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final NumberPath<Integer> missionStatus = createNumber("missionStatus", Integer.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> publishUserId = createNumber("publishUserId", Long.class);

    public final StringPath tags = createString("tags");

    public final NumberPath<Long> timeCoinPrice = createNumber("timeCoinPrice", Long.class);

    public final com.querydsl.sql.PrimaryKey<MissionPo> primary = createPrimaryKey(id);

    public QMission(String variable) {
        super(MissionPo.class, forVariable(variable), "null", "mission");
        addMetadata();
    }

    public QMission(String variable, String schema, String table) {
        super(MissionPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMission(String variable, String schema) {
        super(MissionPo.class, forVariable(variable), schema, "mission");
        addMetadata();
    }

    public QMission(Path<? extends MissionPo> path) {
        super(path.getType(), path.getMetadata(), "null", "mission");
        addMetadata();
    }

    public QMission(PathMetadata metadata) {
        super(MissionPo.class, metadata, "null", "mission");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(accomplishUserId, ColumnMetadata.named("accomplish_user_id").withIndex(9).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(content, ColumnMetadata.named("content").withIndex(6).ofType(Types.VARCHAR).withSize(1024).notNull());
        addMetadata(ctime, ColumnMetadata.named("ctime").withIndex(3).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(2).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(missionStatus, ColumnMetadata.named("mission_status").withIndex(11).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mtime, ColumnMetadata.named("mtime").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(5).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(publishUserId, ColumnMetadata.named("publish_user_id").withIndex(8).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(tags, ColumnMetadata.named("tags").withIndex(7).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(timeCoinPrice, ColumnMetadata.named("time_coin_price").withIndex(10).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

