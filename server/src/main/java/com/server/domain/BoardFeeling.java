package com.server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	long BoardFeelingId;	
	boolean is_like;	
	boolean is_dislike;
	boolean is_checked; 
	long boardId;
	String memberId;
	
			
	
}
