package com.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;
import com.server.domain.QBoard;
import com.server.dto.board.UpdateBoardDTO;
import com.server.exception.board.BoardListNotExistException;
import com.server.exception.board.BoardNotExistException;
import com.server.exception.board.InvalidPagingParameterException;
import com.server.persistence.BoardFeelingRepository;
import com.server.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepo;

	@Autowired
	BoardFeelingRepository boardFeelingRepo;

	/*
	 * @Override public Page<Board> getBoardList(int page, int pageSize) {
	 * 
	 * page--;
	 * 
	 * if (page < 0 || pageSize < 0) { throw new InvalidPagingParameterException();
	 * }
	 * 
	 * Pageable paging = PageRequest.of(page, pageSize); Page<Board> boardList =
	 * boardRepo.findAll(paging); if (boardList.isEmpty()) { throw new
	 * BoardNotExistException(); }
	 * 
	 * return boardList;
	 * 
	 * }
	 */

	public Page<Board> getBoardList(Category category, int page, int pageSize) {
		page--;

		if (page < 0 || pageSize < 1) {

			throw new InvalidPagingParameterException();
		}

		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		// EXERCISE,RESTAURANT,MOVIE,SUTDY,ETC
		if (category.equals(Category.ALL)) {
			builder.and(qboard.enabled.eq(true));
		} else if (category.equals(Category.EXERCISE)) {
			builder.and(qboard.category.eq(category)).and(qboard.enabled.eq(true));
		} else if (category.equals(Category.RESTAURANT)) {
			builder.and(qboard.category.eq(category)).and(qboard.enabled.eq(true));
		} else if (category.equals(Category.MOVIE)) {
			builder.and(qboard.category.eq(category)).and(qboard.enabled.eq(true));
		} else if (category.equals(Category.STUDY)) {
			builder.and(qboard.category.eq(category)).and(qboard.enabled.eq(true));
		} else {
			builder.and(qboard.category.eq(category)).and(qboard.enabled.eq(true));
		}

		Pageable paging = PageRequest.of(page, pageSize);
		Page<Board> boardList = boardRepo.findAll(builder, paging);

		if (boardList.isEmpty()) {
			throw new BoardListNotExistException();
		}

		return boardList;
	}

	@Override
	public Board getBoard(long id) {
		Optional<Board> findBoard = boardRepo.findById(id);

		if (findBoard.isEmpty() || findBoard.get().isEnabled() == false) {
			throw new BoardNotExistException();
		}

		return findBoard.get();

	}

	public void createBoard(Board board) {

		boardRepo.save(board);
	}

	public void deleteBoard(Long id) {

		Board findBoard = boardRepo.findById(id).get();
		findBoard.setEnabled(false);
		boardRepo.save(findBoard);

	}

	public void updateBoard(long boardId, UpdateBoardDTO dto) {
		Optional<Board> option = boardRepo.findById(boardId);

		if (option.isEmpty() || option.get().isEnabled() == false) {
			throw new BoardNotExistException();
		}

		Board findBoard = option.get();
		findBoard.setCategory(Category.valueOf(dto.getCategory()));
		findBoard.setTitle(dto.getTitle());
		findBoard.setContent(dto.getContent());

		boardRepo.save(findBoard);

	}

	public void like(String memberId, Long boardId) {
		
		BoardFeeling boardFeeling = boardFeelingRepo.getBoardFeeling(memberId, boardId);
		Board board = boardRepo.findById(boardId).get();
		
		if (boardFeeling == null) {
			BoardFeeling bf = BoardFeeling.builder().boardId(boardId).memberId(memberId).is_like(true).is_dislike(false).is_checked(true).board(board)
					.build();
			boardFeelingRepo.save(bf);
			board.setBoardLike(board.getBoardLike()+1);
			boardRepo.save(board);
			return;
		}
		

		if (boardFeeling.is_checked() == false) {
			boardFeeling.set_like(true);
			boardFeeling.set_checked(true);
			boardFeelingRepo.save(boardFeeling);
			board.setBoardLike(board.getBoardLike()+1);
			boardRepo.save(board);
			return;
		}

		if (boardFeeling.is_like() == false) {

				boardFeeling.set_like(true);
				boardFeeling.set_dislike(false);
				boardFeeling.set_checked(true);
				boardFeelingRepo.save(boardFeeling);
				board.setBoardLike(board.getBoardLike()+1);
				board.setBoardDislike(board.getBoardDislike()-1);
				boardRepo.save(board);
				return;
		}

		boardFeeling.set_like(false);
		boardFeeling.set_checked(false);
		boardFeelingRepo.save(boardFeeling);
		board.setBoardLike(board.getBoardLike()-1);
		boardRepo.save(board);

	}

	public void disLike(String memberId, Long boardId) {
		BoardFeeling boardFeeling = boardFeelingRepo.getBoardFeeling(memberId, boardId);
		Board board = boardRepo.findById(boardId).get();
		
		if (boardFeeling == null) {
			BoardFeeling bf = BoardFeeling.builder().boardId(boardId).memberId(memberId).is_like(false).is_dislike(true).is_checked(true).board(board)
					.build();
			boardFeelingRepo.save(bf);
			board.setBoardDislike(board.getBoardDislike()+1);
			boardRepo.save(board);
			return;
		}
		
		if(boardFeeling.is_checked()==false) {
			boardFeeling.set_dislike(true);
			boardFeeling.set_checked(true);
			boardFeelingRepo.save(boardFeeling);
			board.setBoardDislike(board.getBoardDislike()+1);
			boardRepo.save(board);
			return;
		}
		
		if(boardFeeling.is_dislike()==false) {
			boardFeeling.set_dislike(true);
			boardFeeling.set_like(false);
			boardFeeling.set_checked(true);
			boardFeelingRepo.save(boardFeeling);
			board.setBoardDislike(board.getBoardDislike()+1);
			board.setBoardLike(board.getBoardLike()-1);
			boardRepo.save(board);
			return;
		}
		
		boardFeeling.set_dislike(false);
		boardFeeling.set_checked(false);
		boardFeelingRepo.save(boardFeeling);
		board.setBoardDislike(board.getBoardDislike()-1);
		boardRepo.save(board);

	}
	
	public int updateBoardCnt(long boardSeq) {
		return boardRepo.updateBoardCnt(boardSeq);
	}

	

}
