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

import com.server.dto.reply.CreateReplyDTO;
import com.server.dto.reply.UpdateReplyDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.service.ReplyService;

@RestController
@RequestMapping("/replys")
//@Api(description = "답글 관련 REST API")
public class ReplyController {

	@Autowired
	ReplyService replyService;

	//@ApiOperation(value = "모집글 답글 생성")
	@PostMapping
	public ResponseEntity<?> createReply(@RequestBody CreateReplyDTO dto) {
		
		replyService.createReply(dto);
		
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("답글 생성 완료").build();
		return ResponseEntity.ok().body(response);
	}
	
	//@ApiOperation(value="모집글 답글 수정")
	@PutMapping("/{replyId}")
	public ResponseEntity<?> updateReply(@PathVariable long replyId,UpdateReplyDTO dto){
		replyService.updateReply(replyId, dto);
		
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("답글 수정 완료").build();
		return ResponseEntity.ok().body(response);
	}
	
	//@ApiOperation(value="모집글 답글 삭제")
	@DeleteMapping("/{replyId}")
	public ResponseEntity<?> deleteReply(@PathVariable long replyId){
		replyService.deleteReply(replyId);
		
		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("답글 삭제 완료").build();
		return ResponseEntity.ok().body(response);
	}
}
