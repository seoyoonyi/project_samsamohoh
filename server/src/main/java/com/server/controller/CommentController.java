package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.comment.CreateCommentDTO;
import com.server.dto.comment.UpdateCommentDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comments")
@Tag(name="Comment", description="댓글 API")
public class CommentController {
	

	@Autowired
	CommentService commentService;
	
	@Operation(summary="댓글 생성",description="")
	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody CreateCommentDTO dto){
		commentService.createComment(dto);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("댓글 생성 완료").build();
		return ResponseEntity.ok().body(response);
		
	}
	
	//@ApiOperation(value="모집글 댓글 수정")
	@PutMapping("/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable long commentId,@RequestBody UpdateCommentDTO dto){
		commentService.updateComment(commentId, dto);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("댓글 수정 완료").build();
		return ResponseEntity.ok().body(response);
	}
	
	
	//@ApiOperation(value="모집글 댓글 삭제")
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable long commentId){
		commentService.deleteComment(commentId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("댓글 삭제 완료").build();
		return ResponseEntity.ok().body(response);
	}


}
