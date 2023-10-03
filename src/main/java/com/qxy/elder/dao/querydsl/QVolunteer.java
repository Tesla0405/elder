package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.VolunteerPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVolunteer is a Querydsl query type for VolunteerPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QVolunteer extends com.querydsl.sql.RelationalPathBase<VolunteerPo> {

    private static final long serialVersionUID = 602797055;

    public static final QVolunteer volunteer = new QVolunteer("volunteer");

    public final DatePath<java.sql.Date> birth = createDate("birth", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idCard = createString("idCard");

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final StringPath tags = createString("tags");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final com.querydsl.sql.PrimaryKey<VolunteerPo> primary = createPrimaryKey(id);

    public QVolunteer(String variable) {
        super(VolunteerPo.class, forVariable(variable), "null", "volunteer");
        addMetadata();
    }

    public QVolunteer(String variable, String schema, String table) {
        super(VolunteerPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVolunteer(String variable, String schema) {
        super(VolunteerPo.class, forVariable(variable), schema, "volunteer");
        addMetadata();
    }

    public QVolunteer(Path<? extends VolunteerPo> path) {
        super(path.getType(), path.getMetadata(), "null", "volunteer");
        addMetadata();
    }

    public QVolunteer(PathMetadata metadata) {
        super(VolunteerPo.class, metadata, "null", "volunteer");
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
        addMetadata(tags, ColumnMetadata.named("tags").withIndex(10).ofType(Types.VARCHAR).withSize(1024).notNull());
        addMetadata(userId, ColumnMetadata.named("user_id").withIndex(9).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

