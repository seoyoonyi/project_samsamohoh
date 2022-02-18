package com.server.dto.comment;

import java.util.ArrayList;
import java.util.List;

import com.server.domain.Comment;
import com.server.domain.Reply;
import com.server.dto.reply.ShowReplyDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="댓글 DTO")
public class ShowCommentDTO {
	@Schema(description="댓글 아이디(PK)")
	long commentId;
	@Schema(description="댓글 아이디(PK)",example="댓글 내용")
	String comment;
	@Schema(description="댓글을 작성한 사용자 아이디",example="댓글을 작성한 사용자 아이디")
	String commentAuthor;
	@Schema(description="댓글에 달린 답변 리스트")
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
