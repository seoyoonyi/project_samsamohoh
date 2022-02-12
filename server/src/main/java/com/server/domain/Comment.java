package com.server.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@ToString(exclude= {"replyList","member","board"})
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long commentId;
	
	String comment;
	
	@ManyToOne
	@JoinColumn(name="id",nullable=false)
	Member member;
	
	@ManyToOne
	@JoinColumn(name="boardId",nullable=false)
	Board board;	
	
	@JsonIgnore
	@OneToMany(mappedBy="comment",fetch=FetchType.EAGER)
	List<Reply> replyList;
	
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
