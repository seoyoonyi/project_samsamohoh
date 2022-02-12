package com.server.dto.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.server.domain.Board;
import com.server.domain.BoardFeeling;
import com.server.domain.Category;
import com.server.domain.Comment;
import com.server.dto.comment.ShowCommentDTO;

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
	private List<ShowCommentDTO> comment = new ArrayList<ShowCommentDTO>();

	public AuthenticatedShowBoardDTO(List<Object[]> findBoard, BoardFeeling boardFeeling) {
		this.boardId = ((Board) (findBoard.get(0)[0])).getBoardId();
		this.category = ((Board) (findBoard.get(0)[0])).getCategory();
		this.title = ((Board) (findBoard.get(0)[0])).getTitle();
		this.content = ((Board) (findBoard.get(0)[0])).getContent();
		this.cnt = ((Board) (findBoard.get(0)[0])).getCnt();
		this.boardLike = ((Board) (findBoard.get(0)[0])).getBoardLike();
		this.boardDislike = ((Board) (findBoard.get(0)[0])).getBoardDislike();
		this.regisDate = ((Board) (findBoard.get(0)[0])).getRegisDate();
		this.userId = ((Board) (findBoard.get(0)[0])).getMember().getId();
		this.nickName = ((Board) (findBoard.get(0)[0])).getMember().getNickName();
		for (Comment c : ((Board) (findBoard.get(0)[0])).getCommentList()) {
			if (c.isEnabled() == true) {
				this.comment.add(new ShowCommentDTO(c));
			}
		}
		
		if(comment.size()==0) {
			comment = null;
		}
		
		
		if (boardFeeling == null) {
			this.likeChecked = false;
			this.dislikeChecked = false;
		} else {
			this.likeChecked = boardFeeling.is_like();
			this.dislikeChecked = boardFeeling.is_dislike();
		}
	}

}
