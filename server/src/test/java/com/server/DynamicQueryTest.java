package com.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.server.domain.Board;
import com.server.domain.QBoard;
import com.server.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryTest {

	@Autowired BoardRepository boardRepo;
	
	/*@Test
	public void testDynamicQuery() {
		String searchCondition = "id";
		String searchKeyword = "city6213";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("id")) {
			builder.and(qboard.title.like("%"+searchKeyword+"%"));
		}else if(searchCondition.equals("email")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}else {
			builder.and(qboard.title.eq("0").and(qboard.content.eq("")));
		}
		
		Pageable paging = PageRequest.of(0,5);
		
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
		
	}*/
	@Test
	public void updateTest() {
		boardRepo.updateBoardCnt(1L);
	}
}
