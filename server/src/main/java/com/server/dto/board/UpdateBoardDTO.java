package com.server.dto.board;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.server.domain.Board;
import com.server.domain.Category;
import com.server.validation.EnumPattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description="게시글 업데이트 DTO")
public class UpdateBoardDTO {
	@Schema(description="게시글 카테고리",example="카테고리")
	@EnumPattern(regexp="(ALL|EXERCISE|RESTAURANT|MOVIE|STUDY|ETC)",message="카테고리값은 (ALL|EXERCISE|RESTAURANT|MOVIE|STUDY|ETC)중 하나입니다.")
	Category category;
	
	@Schema(description="게시글 제목",example="게시글 제목")
	@NotEmpty(message="빈값은 입력할 수 없습니다.")
	@Pattern(regexp=".{0,255}",message="제목은 1글자이상 255글자 이하입니다.")
	String title;
	
	@Schema(description="게시글 내용",example="게시글 내용")
	@NotEmpty(message="빈값은 입력할 수 없습니다.")
	@Pattern(regexp=".{0,255}",message="제목은 1글자이상 255글자 이하입니다.")
	String content;

	public Board toEntity() {
		return Board.builder().category(category).title(title).content(content).build();
	}

}
