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

	private String title;

	private String content;

	
	@Column(insertable=false,columnDefinition = "datetime default current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regisDate;
	@Column(columnDefinition= "bigint default 0",insertable=false)
	private long cnt;
	@Column(columnDefinition = "bigint default 0", insertable=false)
	private long good;
	@Column(columnDefinition = "bigint default 0", insertable = false)
	private long bad;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Member member;

}
