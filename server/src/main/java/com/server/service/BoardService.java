package com.server.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.server.domain.Board;

public interface BoardService {
	
	public Page<Board> getBoardList(Pageable paging);
	
	public Optional<Board> getBoard(long id);
		
	public void createBoard(Board board);
}
