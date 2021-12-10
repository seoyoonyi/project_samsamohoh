package com.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.server.domain.Board;
import com.server.domain.Member;
import com.server.persistence.BoardRepository;
import com.server.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	BoardRepository boardRepo;

	@Test
	public void makeMember() {
		Member member = new Member();
		member.setId("admin123");
		member.setName("시조새");
		member.setPassword("454568aS!");
		member.setEmail("jose@naver.com");
		memberRepo.save(member);

		for (int i = 0; i < 5; i++) {
			Board board = new Board();
			board.setTitle("등록글 제목"+i);
			board.setContent("등록글 내용"+i);
			board.setMember(member);

			boardRepo.save(board);
		}

	}

}
