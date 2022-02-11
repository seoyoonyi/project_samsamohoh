package com.server.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
	long boardId;
	String memberId;
	String comment;
}
