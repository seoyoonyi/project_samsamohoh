package com.server.service;

import com.server.dto.comment.CreateCommentDTO;
import com.server.dto.comment.UpdateCommentDTO;

public interface CommentService {
	
	public void createComment(CreateCommentDTO dto);
	
	public void updateComment(long commentId,UpdateCommentDTO dto);
	
	public void deleteComment(long commentId) ;

}
