package com.server.dto.board;

import java.util.Date;

import com.server.domain.Board;
import com.server.domain.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="게시글 리스트 조회 DTO")
public class ShowBoardListDTO {
	@Schema(description="게시글 아이디(PK)",example="게시글 아이디(PK)")
	private long boardId;
	@Schema(description="게시글 카테고리",example="게시글 카테고리")
	private Category category;
	@Schema(description="게시글 제목",example="게시글 제목")
	private String title;
	@Schema(description="게시글 내용",example="게시글 내용")
	private String content;
	@Schema(description="게시글 조회수",example="게시글 조회수")
	private long cnt;
	@Schema(description="게시글 좋아요 갯수",example="게시글 좋아요 갯수")
	private long boardLike;
	@Schema(description="게시글 싫어요 갯수",example="게시글 싫어요 갯수")
	private long boardDislike;
	@Schema(description="게시글 생성 날짜",example="게시글 생성 날짜")
	private Date regisDate;
	@Schema(description="게시글을 작성한 사용자의 아이디",example="게시글을 작성한 사용자의 아이디")
	private String userId;
	@Schema(description="게시글을 작성한 사용자의 닉네임",example="게시글을 작성한 사용자의 닉네임")
	private String nickName;

	public ShowBoardListDTO(Board board) {
		this.boardId = board.getBoardId();
		this.category = board.getCategory();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.cnt = board.getCnt();
		this.boardLike = board.getBoardLike();
		this.boardDislike = board.getBoardDislike();
		this.regisDate = board.getRegisDate();
		this.userId = board.getMember().getId();
		this.nickName = board.getMember().getNickName();
	}
}
