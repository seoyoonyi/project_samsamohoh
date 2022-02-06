package com.server.persistence;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.server.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	@Transactional
	@Modifying
	@Query("update Board b set b.cnt = b.cnt+1 where b.boardId = ?1")
	public int updateBoardCnt(long boardId);
	
	@Query("select b from Board b where boardId = ?1")
	public Optional<Board> getBoard(long boardId);

}
