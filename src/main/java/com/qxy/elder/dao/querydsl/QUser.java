package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.UserPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for UserPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QUser extends com.querydsl.sql.RelationalPathBase<UserPo> {

    private static final long serialVersionUID = 126474388;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> role = createNumber("role", Integer.class);

    public final NumberPath<Long> timeCoin = createNumber("timeCoin", Long.class);

    public final StringPath username = createString("username");

    public final com.querydsl.sql.PrimaryKey<UserPo> primary = createPrimaryKey(id);

    public QUser(String variable) {
        super(UserPo.class, forVariable(variable), "null", "user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(UserPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(String variable, String schema) {
        super(UserPo.class, forVariable(variable), schema, "user");
        addMetadata();
    }

    public QUser(Path<? extends UserPo> path) {
        super(path.getType(), path.getMetadata(), "null", "user");
        addMetadata();
    }

    public QUser(PathMetadata metadata) {
        super(UserPo.class, metadata, "null", "user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(ctime, ColumnMetadata.named("ctime").withIndex(3).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(2).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(mtime, ColumnMetadata.named("mtime").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(5).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(password, ColumnMetadata.named("password").withIndex(7).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(role, ColumnMetadata.named("role").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(timeCoin, ColumnMetadata.named("time_coin").withIndex(9).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(username, ColumnMetadata.named("username").withIndex(6).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

