package com.server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardFeeling is a Querydsl query type for BoardFeeling
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardFeeling extends EntityPathBase<BoardFeeling> {

    private static final long serialVersionUID = -1145841374L;

    public static final QBoardFeeling boardFeeling = new QBoardFeeling("boardFeeling");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final BooleanPath is_checked = createBoolean("is_checked");

    public final BooleanPath is_dislike = createBoolean("is_dislike");

    public final BooleanPath is_like = createBoolean("is_like");

    public final StringPath memberId = createString("memberId");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QBoardFeeling(String variable) {
        super(BoardFeeling.class, forVariable(variable));
    }

    public QBoardFeeling(Path<? extends BoardFeeling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardFeeling(PathMetadata metadata) {
        super(BoardFeeling.class, metadata);
    }

}

