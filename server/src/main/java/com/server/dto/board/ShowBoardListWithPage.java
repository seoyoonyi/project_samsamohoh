package com.server.dto.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.server.domain.Board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description="게시글 리스트와 페이지 정보 DTO")
public class ShowBoardListWithPage {
	
	@Schema(description="게시글 리스트 페이지 정보")
	BoardPageDTO page;
	@Schema(description="게시글 리스트")
	List<ShowBoardListDTO> items = new ArrayList<ShowBoardListDTO>();
	
	
	public ShowBoardListWithPage(Page<Board> boardList) {
		this.page = new BoardPageDTO(boardList);
		for(Board board : boardList.getContent()) {
			items.add(new ShowBoardListDTO(board));
		}
	}
}
