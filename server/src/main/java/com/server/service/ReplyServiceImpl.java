package com.server.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.domain.Comment;
import com.server.domain.Member;
import com.server.domain.Reply;
import com.server.dto.reply.CreateReplyDTO;
import com.server.dto.reply.UpdateReplyDTO;
import com.server.exception.comment.CommentNotExistException;
import com.server.exception.member.MemberNotExistException;
import com.server.exception.reply.ReplyNotExistException;
import com.server.persistence.CommentRepository;
import com.server.persistence.MemberRepository;
import com.server.persistence.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ReplyRepository replyRepo;
	
	public void createReply(CreateReplyDTO dto) {
		Optional<Comment> commentOption = commentRepo.findById(dto.getCommentId());
		Optional<Member>  memberOption = memberRepo.findById(dto.getMemberId());
		if(commentOption.isEmpty()) {
			throw new CommentNotExistException();
		}
		
		if(memberOption.isEmpty()) {
			throw new MemberNotExistException();
		}
		
		Reply reply = new Reply();
		reply.setComment(commentOption.get());
		reply.setMember(memberOption.get());
		reply.setReplyComment(dto.getReplyComment());
		
		replyRepo.save(reply);
	}
	
	public void updateReply(long replyId,UpdateReplyDTO dto) {
		Optional<Reply> option = replyRepo.findById(replyId);
		if(option.isEmpty()) {
			throw new ReplyNotExistException();
		}
		
		Reply reply = option.get();
		reply.setReplyComment(dto.getReplyComment());
		reply.setUpdateDate(new Date());
		
		replyRepo.save(reply);
	}
	
	public void deleteReply(long replyId) {
		Optional<Reply> option = replyRepo.findById(replyId);
		if(option.isEmpty()) {
			throw new ReplyNotExistException();
		}
		Reply reply = option.get();
		reply.setEnabled(false);
		reply.setDeleteDate(new Date());
		
		replyRepo.save(reply);
			
	}

}
