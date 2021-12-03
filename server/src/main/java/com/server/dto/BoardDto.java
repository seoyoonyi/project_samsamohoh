package com.server.dto;

import java.util.Date;

import com.server.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	
	private long seq;
	private String title;
	private String content;
	private Date regisDate;
	private long cnt;
	private long good;
	private long bad;
	private String userId;
	
	@Builder
	public BoardDto(Board board) {
		this.seq = board.getSeq();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.regisDate = board.getRegisDate();
		this.cnt = board.getCnt();
		this.good = board.getGood();
		this.bad = board.getBad();
		this.userId = board.getMember().getId();
		
	}
	
}
