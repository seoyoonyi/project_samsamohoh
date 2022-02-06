package com.server.dto.board;

import java.util.Date;

import com.server.domain.Board;
import com.server.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ShowBoardDTO {
	
	private long seq;
	private Category category;
	private String title;
	private String content;
	private long cnt;
	private long boardLike;
	private long boardDislike;
	private Date regisDate;
	private String userId;
	private String nickName;
	private boolean like_checked;
	private boolean dislike_checked;
	
	public ShowBoardDTO(Board board) {
		this.seq = board.getSeq();
		this.category = board.getCategory();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.cnt = board.getCnt();
		this.boardLike = board.getBoardLike();
		this.boardDislike = board.getBoardDislike();
		this.regisDate = board.getRegisDate();
		this.userId = board.getMember().getId();
		this.nickName = board.getMember().getNickName();
		this.like_checked = board.getBoardFeelingList().get(0).is_like();
		this.dislike_checked = board.getBoardFeelingList().get(0).is_dislike();
	}
	
}
