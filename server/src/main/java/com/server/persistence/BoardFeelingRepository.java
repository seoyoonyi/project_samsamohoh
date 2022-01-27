package com.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.server.domain.BoardFeeling;

public interface BoardFeelingRepository extends JpaRepository<BoardFeeling,Long>{
	
	@Query("select b from BoardFeeling b where b.memberId = ?1 and b.boardId = ?2")
	public BoardFeeling getBoardFeeling(String memberId,Long boardId);
	
	
	@Query("select count(*) from BoardFeeling b where b.boardId = ?1 and b.is_like = true")
	public long getBoardLikeNum(Long boardId);
}
