package com.server.dto.board;

import java.util.Date;

import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;

import lombok.Data;

@Data
public class AuthenticatedShowBoardDTO {

	private long boardId;
	private Category category;
	private String title;
	private String content;
	private long cnt;
	private long boardLike;
	private long boardDislike;
	private Date regisDate;
	private String userId;
	private String nickName;
	private boolean likeChecked;
	private boolean dislikeChecked;

	public AuthenticatedShowBoardDTO(Board board, BoardFeeling boardFeeling) {
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
		if (boardFeeling == null) {
			this.likeChecked = false;
			this.dislikeChecked = false;
		}else {
			this.likeChecked = boardFeeling.is_like();
			this.dislikeChecked = boardFeeling.is_dislike();
		}
	}

}
