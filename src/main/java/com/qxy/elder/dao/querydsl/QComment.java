package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.CommentPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QComment is a Querydsl query type for CommentPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QComment extends com.querydsl.sql.RelationalPathBase<CommentPo> {

    private static final long serialVersionUID = -708263148;

    public static final QComment comment = new QComment("comment");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final NumberPath<Long> missionId = createNumber("missionId", Long.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final com.querydsl.sql.PrimaryKey<CommentPo> primary = createPrimaryKey(id);

    public QComment(String variable) {
        super(CommentPo.class, forVariable(variable), "null", "comment");
        addMetadata();
    }

    public QComment(String variable, String schema, String table) {
        super(CommentPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QComment(String variable, String schema) {
        super(CommentPo.class, forVariable(variable), schema, "comment");
        addMetadata();
    }

    public QComment(Path<? extends CommentPo> path) {
        super(path.getType(), path.getMetadata(), "null", "comment");
        addMetadata();
    }

    public QComment(PathMetadata metadata) {
        super(CommentPo.class, metadata, "null", "comment");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(content, ColumnMetadata.named("content").withIndex(2).ofType(Types.VARCHAR).withSize(1024).notNull());
        addMetadata(ctime, ColumnMetadata.named("ctime").withIndex(5).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(4).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(missionId, ColumnMetadata.named("mission_id").withIndex(7).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(mtime, ColumnMetadata.named("mtime").withIndex(6).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(userId, ColumnMetadata.named("user_id").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

