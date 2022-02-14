package com.server.dto.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.server.domain.Board;
import com.server.domain.Category;
import com.server.domain.Comment;
import com.server.dto.comment.ShowCommentDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Schema(description="비로그인 사용자가 게시글을 조회할때의 DTO")
public class ShowBoardDTO {

	@Schema(description="게시글 아이디")
	private long boardId;
	@Schema(description="게시글 카테고리")
	private Category category;
	@Schema(description="게시글 제목")
	private String title;
	@Schema(description="게시글 내용")
	private String content;
	@Schema(description="게시글 조회수")
	private long cnt;
	@Schema(description="게시글 좋아요 갯수")
	private long boardLike;
	@Schema(description="게시글 싫어요 갯수")
	private long boardDislike;
	@Schema(description="게시글 생성 날짜")
	private Date regisDate;
	@Schema(description="게시글을 작성한 사용자 아이디")
	private String userId;
	@Schema(description="게시글을 작성한 사용자 닉네임")
	private String nickName;
	@Schema(description="게시글에 달린 댓글과 답글")
	private List<ShowCommentDTO> commentAndReply = new ArrayList<ShowCommentDTO>();
	
	public ShowBoardDTO(Board findBoard) {
		this.boardId = findBoard.getBoardId();
		this.category = findBoard.getCategory();
		this.title = findBoard.getTitle();
		this.content = findBoard.getContent();
		this.cnt = findBoard.getCnt();
		this.boardLike = findBoard.getBoardLike();
		this.boardDislike = findBoard.getBoardDislike();
		this.regisDate = findBoard.getRegisDate();
		this.userId = findBoard.getMember().getId();
		this.nickName = findBoard.getMember().getNickName();
		for (Comment comment : findBoard.getCommentList()) {
			if (comment.isEnabled() == true) {
				this.commentAndReply.add(new ShowCommentDTO(comment));
			}
		}

	}


}
