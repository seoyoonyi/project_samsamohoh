package com.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.server.domain.Board;
import com.server.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository boardRepo;
	
	@Override
	public Page<Board> getBoardList(Pageable paging) {
		
		return boardRepo.findAll(paging);
	}

	@Override
	public Optional<Board> getBoard(long id) {
		return boardRepo.findById(id);
	}
	
	public void createBoard(Board board) {
		boardRepo.save(board);
	}
	
}
