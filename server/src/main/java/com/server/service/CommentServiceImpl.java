package com.server.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.domain.Board;
import com.server.domain.Comment;
import com.server.domain.Member;
import com.server.dto.comment.CreateCommentDTO;
import com.server.dto.comment.UpdateCommentDTO;
import com.server.exception.board.BoardNotExistException;
import com.server.exception.comment.CommentNotExistException;
import com.server.exception.member.MemberNotExistException;
import com.server.persistence.BoardRepository;
import com.server.persistence.CommentRepository;
import com.server.persistence.MemberRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	public void createComment(CreateCommentDTO dto) {
		Optional<Board> findBoard = boardRepo.findById(dto.getBoardId());
		Optional<Member> findMember = memberRepo.findById(dto.getMemberId());
		if(findBoard.isEmpty()) {
			throw new BoardNotExistException();
		}
		
		if(findMember.isEmpty()) {
			throw new MemberNotExistException();
		}
		
		Comment comment = new Comment();
		comment.setBoard(findBoard.get());
		comment.setMember(findMember.get());
		comment.setComment(dto.getComment());
		commentRepo.save(comment);
	}
	
	public void updateComment(long commentId,UpdateCommentDTO dto) {
		Optional<Comment> option = commentRepo.findById(commentId);
	
		if(option.isEmpty()) {
			throw new CommentNotExistException();
		}
		
		Comment findComment = option.get();
		findComment.setComment(dto.getComment());
		findComment.setUpdateDate(new Date());
		commentRepo.save(findComment);
	}
	
	public void deleteComment(long commentId) {
		Optional<Comment> option = commentRepo.findById(commentId);
		
		if(option.isEmpty()) {
			throw new CommentNotExistException();
		}
		
		Comment findComment = option.get();
		findComment.setEnabled(false);
		findComment.setDeleteDate(new Date());
		commentRepo.save(findComment);
	}
}
