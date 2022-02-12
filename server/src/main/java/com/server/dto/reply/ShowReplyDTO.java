package com.server.dto.reply;

import com.server.domain.Reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowReplyDTO {
	long replyId;
	String replyComment;
	String replyAuthor;
	
	public ShowReplyDTO(Reply reply) {
		this.replyId = reply.getReplyId();
		this.replyComment = reply.getReplyComment();
		this.replyAuthor = reply.getMember().getId();
	}
}
