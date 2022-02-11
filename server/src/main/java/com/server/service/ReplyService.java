package com.server.service;

import com.server.dto.reply.CreateReplyDTO;
import com.server.dto.reply.UpdateReplyDTO;

public interface ReplyService {
	public void createReply(CreateReplyDTO dto);
	
	public void updateReply(long replyId,UpdateReplyDTO dto);
	
	public void deleteReply(long replyId);
}
