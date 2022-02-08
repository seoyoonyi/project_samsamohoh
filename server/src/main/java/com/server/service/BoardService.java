package com.server.service;

import org.springframework.data.domain.Page;

import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;
import com.server.dto.board.UpdateBoardDTO;

public interface BoardService {
	
	public Page<Board> getBoardList(Category category,int page,int pageSize);
	
	public Board getBoard(long BoardSeq);
		
	public void createBoard(Board board);
	
	public void deleteBoard(Long id);
	
	public void updateBoard(long boardId,UpdateBoardDTO dto);
	
	public void like(String memberId, long boardId);
	
	public void disLike(String memberId, long boardId);
	
	public int updateBoardCnt(long boardSeq);
	
	public BoardFeeling getBoardFeeling(String memberId,long boardId);
}
