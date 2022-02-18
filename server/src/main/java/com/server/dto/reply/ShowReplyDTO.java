package com.server.dto.reply;

import com.server.domain.Reply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="답글 DTO")
public class ShowReplyDTO {
	@Schema(description="답글 아이디(PK)")
	long replyId;
	@Schema(description="답글내용",example="답글내용")
	String replyComment;
	@Schema(description="답글을 작성한 사용자 아이디",example="답글을 작성한 사용자 아이디")
	String replyAuthor;
	
	public ShowReplyDTO(Reply reply) {
		this.replyId = reply.getReplyId();
		this.replyComment = reply.getReplyComment();
		this.replyAuthor = reply.getMember().getId();
	}
}
