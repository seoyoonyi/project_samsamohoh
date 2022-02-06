package com.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	
	@Enumerated(EnumType.STRING)
	private Category category;

	private String title;

	private String content;
	
	@Column(insertable = false,columnDefinition="boolean default 1")
	private boolean enabled;
	
	@Column(insertable=false,columnDefinition = "datetime default current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regisDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Column(columnDefinition= "bigint default 0",insertable=false)
	private long cnt;
	
	@Column(columnDefinition = "bigint default 0", insertable=false)
	private long boardLike;
	
	@Column(columnDefinition = "bigint default 0", insertable = false)
	private long boardDislike;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Member member;
	 
 
}
