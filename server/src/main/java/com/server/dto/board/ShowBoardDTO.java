package com.server.dto.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.server.domain.Board;
import com.server.domain.Category;
import com.server.domain.Comment;
import com.server.dto.comment.ShowCommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ShowBoardDTO {

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
	private List<ShowCommentDTO> comment = new ArrayList<ShowCommentDTO>();

	public ShowBoardDTO(List<Object[]> findBoard) {
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
		System.out.println(((Board) (findBoard.get(0)[0])).getCommentList().size());
		for (Comment c : ((Board) (findBoard.get(0)[0])).getCommentList()) {
			if (c.isEnabled() == true) {
				this.comment.add(new ShowCommentDTO(c));
			}
		}

		// this.comment.put("reply",reply);
	}

}
