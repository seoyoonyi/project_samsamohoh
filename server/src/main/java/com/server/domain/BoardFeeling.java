package com.server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BoardFeeling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long seq;
	long boardId;	
	String memberId;	
	boolean is_like;	
	boolean is_dislike;
	boolean is_checked; 
	
	@ManyToOne
	@JoinColumn(name="id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="seq")
	private Board board;
	
}
