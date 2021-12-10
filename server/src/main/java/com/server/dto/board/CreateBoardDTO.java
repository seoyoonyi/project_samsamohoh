package com.server.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.domain.Board;
import com.server.domain.Category;
import com.server.domain.Member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description="이 DTO는 모집글을 생성하기위한 DTO입니다.")
public class CreateBoardDTO {
	@ApiModelProperty(notes ="모집글 카테고리")
	private Category category;
	@ApiModelProperty(notes="모집글 제목")
	private String title;
	@ApiModelProperty(notes="모집글 내용")
	private String content;
	


	public CreateBoardDTO(@JsonProperty("category") Category category, String title, String content) {
		this.category = category;
		this.title = title;
		this.content = content;
		
	}

	public Board toEntity(Member member) {
		return Board.builder().category(category).title(title).content(content).member(member).build();
	}
}
