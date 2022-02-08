package com.server.dto.board;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.server.domain.Board;
import com.server.domain.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBoardDTO {
	
	@Pattern(regexp="(ALL|EXERCISE|RESTAURANT|MOVIE|STUDY|ETC)",message="카테고리값은 ALL,EXERCISE,RESTAURANT,MOVIE,STUDY,ETC 중 하나입니다.")
	String category;
	
	@NotEmpty(message="빈값은 입력할 수 없습니다.")
	@Pattern(regexp=".{0,255}",message="제목은 1글자이상 255글자 이하입니다.")
	String title;
	
	@NotEmpty(message="빈값은 입력할 수 없습니다.")
	@Pattern(regexp=".{0,255}",message="제목은 1글자이상 255글자 이하입니다.")
	String content;

	public Board toEntity() {
		return Board.builder().category(Category.valueOf(category)).title(title).content(content).build();
	}

}
