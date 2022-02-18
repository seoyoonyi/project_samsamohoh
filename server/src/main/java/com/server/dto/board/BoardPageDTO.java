package com.server.dto.board;

import org.springframework.data.domain.Page;

import com.server.domain.Board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="게시글 리스트 페이지 정보 DTO")
public class BoardPageDTO {
	@Schema(description="게시글 리스트 페이지 정보 DTO")
	int totalPage;
	@Schema(description="게시글 리스트 페이지 정보 DTO")
	int pageSize;
	@Schema(description="게시글 리스트 페이지 정보 DTO")
	int currentPage;
	@Schema(description="게시글 리스트 페이지 정보 DTO")
	long totalCount;
	
	public BoardPageDTO(Page<Board> boardList) {
		this.totalPage = boardList.getTotalPages();
		this.pageSize = boardList.getSize();
		this.currentPage = boardList.getNumber()+1;
		this.totalCount = boardList.getTotalElements();
	}
}
