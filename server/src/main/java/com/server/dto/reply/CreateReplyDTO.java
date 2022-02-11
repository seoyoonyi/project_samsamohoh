package com.server.dto.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReplyDTO {
	long commentId;
	String memberId;
	String replyComment;
}
