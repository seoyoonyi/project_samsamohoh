package com.server.persistence.customrepo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.Board;
import com.server.domain.QBoard;
import com.server.domain.QComment;
import com.server.domain.QReply;

public class BoardRepositoryImpl implements CustomBoardRepository{
	
	@Autowired
	private final JPAQueryFactory jpaQueryFactory;
	
	private final QBoard board = QBoard.board;
	
	private final QComment comment = QComment.comment1;
	
	private final QReply reply = QReply.reply;
	
	public BoardRepositoryImpl(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}
	
	public Board getBoardWithCommentAndReply(long boardId){
		
		return jpaQueryFactory
				.select(board)
				.from(board)
				.leftJoin(board.commentList,comment)
				.fetchJoin()
				.leftJoin(comment.replyList,reply)
				.fetchJoin()
				.where(board.boardId.eq(boardId))
				.fetchOne();
				
	}

}
