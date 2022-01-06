package com.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.server.domain.Board;
import com.server.domain.Category;
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
	public void makeMember() throws InterruptedException {
		Category[] cate = Category.values();
		for (int i = 1; i <= 10; i++) {
			Member member = new Member();
			member.setId("use" + i);
			member.setNickName("사용자" + i);
			member.setPassword("12345aS!" + i);
			member.setEmail("email" + i + "@naver.com");
			memberRepo.save(member);
			Thread.sleep(1000);
			for (int j = 1; j <= 10; j++) {
				Board board = new Board();
				board.setCategory(cate[j%5]);
				board.setTitle("등록글 제목" + j);
				board.setContent(member.getNickName()+"이 등록글 내용" + j);
				board.setMember(member);
				boardRepo.save(board);
				Thread.sleep(2000L);
			}

		}

	}

}
