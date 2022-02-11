package com.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long replyId;
	
	String replyComment;

	
	@ManyToOne
	@JoinColumn(name="commentId",nullable=false)
	Comment comment;
	
	@ManyToOne
	@JoinColumn(name="id",nullable=false)
	Member member;
	
	@Column(insertable=false,columnDefinition = "boolean default 1")
	boolean enabled;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false,columnDefinition = "datetime default current_timestamp")
	Date regisDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date updateDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date deleteDate;
}
