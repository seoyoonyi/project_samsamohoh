package com.server.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.server.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	@Transactional
	@Modifying
	@Query("update Board b set b.cnt = b.cnt+1 where b.boardId = ?1")
	public int updateBoardCnt(long boardId);
	
	@Query("select b from Board b where boardId = ?1")
	public Optional<Board> getBoard(long boardId);
	
	@Query("select b,c,r from Board b left join b.commentList c left join c.replyList r")
	public List<Object[]> getBoardList();

}
