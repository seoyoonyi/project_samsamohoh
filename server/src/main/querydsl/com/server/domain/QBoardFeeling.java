package com.server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardFeeling is a Querydsl query type for BoardFeeling
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardFeeling extends EntityPathBase<BoardFeeling> {

    private static final long serialVersionUID = -1145841374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardFeeling boardFeeling = new QBoardFeeling("boardFeeling");

    public final QBoard board;

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final BooleanPath is_checked = createBoolean("is_checked");

    public final BooleanPath is_dislike = createBoolean("is_dislike");

    public final BooleanPath is_like = createBoolean("is_like");

    public final QMember member;

    public final StringPath memberId = createString("memberId");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QBoardFeeling(String variable) {
        this(BoardFeeling.class, forVariable(variable), INITS);
    }

    public QBoardFeeling(Path<? extends BoardFeeling> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardFeeling(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardFeeling(PathMetadata metadata, PathInits inits) {
        this(BoardFeeling.class, metadata, inits);
    }

    public QBoardFeeling(Class<? extends BoardFeeling> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

