package com.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "boardList")
@Entity
public class Member {
	
	@Id
	private String id;
	
	private String password;
	
	private String nickName;

	private String email;
	
	private String imagePath;

	@Enumerated(EnumType.STRING)
	@Column(insertable = false, columnDefinition = "varchar(15) default 'ROLE_MEMBER'")
	private Role role;

	@Column(insertable = false, columnDefinition = "boolean default 1")
	private boolean enabled;

	@Column(insertable = false, updatable = false, columnDefinition = "datetime default current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regisDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>(); 
	
 
}
