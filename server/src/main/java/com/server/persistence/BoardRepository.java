package com.server.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.server.domain.Board;
import com.server.persistence.customrepo.CustomBoardRepository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>,CustomBoardRepository {
	
	
	@Transactional
	@Modifying
	@Query("update Board b set b.cnt = b.cnt+1 where b.boardId = ?1")
	public int updateBoardCnt(long boardId);
	
	@Query("select b,c from Board b left join b.commentList c left join c.replyList r  where b.id = ?1 order by c.regisDate desc")
	public List<Object[]> getBoard(long boardId);
	
	@Query("select  b,c,r,count(b),count(c) from Board b left join b.commentList c left join c.replyList r")
	public List<Object[]> getBoardList();
	
	

}
