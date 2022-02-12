package com.server.dto.comment;

import java.util.ArrayList;
import java.util.List;

import com.server.domain.Comment;
import com.server.domain.Reply;
import com.server.dto.reply.ShowReplyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCommentDTO {

	long commentId;
	String comment;
	String commentAuthor;
	List<ShowReplyDTO> replyList = new ArrayList<ShowReplyDTO>();

	public ShowCommentDTO(Comment comment) {
		this.commentId = comment.getCommentId();
		this.comment = comment.getComment();
		this.commentAuthor = comment.getMember().getId();
		for (Reply reply : comment.getReplyList()) {
			if (reply.isEnabled() == true) {
				replyList.add(new ShowReplyDTO(reply));
			}
		}
		
		if(replyList.size()==0) {
			replyList = null;
		}
	}

}
