package com.qxy.elder.dao.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.qxy.elder.dao.querydsl.pos.TestPo;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTest is a Querydsl query type for TestPo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTest extends com.querydsl.sql.RelationalPathBase<TestPo> {

    private static final long serialVersionUID = 85334939;

    public static final QTest test = new QTest("test");

    public final DateTimePath<java.sql.Timestamp> ctime = createDateTime("ctime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final DateTimePath<java.sql.Timestamp> mtime = createDateTime("mtime", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<TestPo> primary = createPrimaryKey(id);

    public QTest(String variable) {
        super(TestPo.class, forVariable(variable), "null", "test");
        addMetadata();
    }

    public QTest(String variable, String schema, String table) {
        super(TestPo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTest(String variable, String schema) {
        super(TestPo.class, forVariable(variable), schema, "test");
        addMetadata();
    }

    public QTest(Path<? extends TestPo> path) {
        super(path.getType(), path.getMetadata(), "null", "test");
        addMetadata();
    }

    public QTest(PathMetadata metadata) {
        super(TestPo.class, metadata, "null", "test");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(ctime, ColumnMetadata.named("ctime").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(3).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(mtime, ColumnMetadata.named("mtime").withIndex(5).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

