package com.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long indexId;
	
	String commnet;
	
	String boardId;
	
	String layer;
	
	String orderNum;
	
	String groupNumber;
	
	String memberId;
	
	@Column(columnDefinition = "boolean default 1")
	boolean enabled;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "datetime default current_timestamp")
	Date regisDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date updateDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date deleteDate;
	
}
