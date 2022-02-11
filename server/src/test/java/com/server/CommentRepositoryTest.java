package com.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.server.domain.Board;
import com.server.domain.Comment;
import com.server.domain.Reply;
import com.server.persistence.BoardRepository;
import com.server.persistence.CommentRepository;
import com.server.persistence.MemberRepository;
import com.server.persistence.ReplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	ReplyRepository replyRepo;
	
	@Test
	public void joinTest() {
		/*Member findMember = memberRepo.findById("city6213").get();
		Board findBoard = boardRepo.findById(1L).get();
		
		System.out.println(findMember.toString());
		System.out.println(findBoard.toString());
		Comment comment = new Comment();
		comment.setComment("몇시쯤 모이나요?");
		comment.setMember(findMember);
		comment.setBoard(findBoard);
		commentRepo.save(comment);
		
		Reply reply = new Reply();
		reply.setReplyComment("3시쯤 모입니다");
		reply.setComment(comment);
		reply.setMember(findMember);
		replyRepo.save(reply);*/
		for(Object[] o : boardRepo.getBoardList()) {
			System.out.println(((Board)o[0]).getBoardLike());
			System.out.println(((Comment)o[1]).getComment());
			System.out.println(((Reply)o[2]).getReplyComment());
		}
		
		
	}
}
