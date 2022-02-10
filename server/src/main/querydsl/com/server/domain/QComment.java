package com.server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 1395524723L;

    public static final QComment comment = new QComment("comment");

    public final StringPath boardId = createString("boardId");

    public final StringPath commnet = createString("commnet");

    public final DateTimePath<java.util.Date> deleteDate = createDateTime("deleteDate", java.util.Date.class);

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath groupNumber = createString("groupNumber");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final StringPath layer = createString("layer");

    public final StringPath memberId = createString("memberId");

    public final StringPath orderNum = createString("orderNum");

    public final DateTimePath<java.util.Date> regisDate = createDateTime("regisDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

