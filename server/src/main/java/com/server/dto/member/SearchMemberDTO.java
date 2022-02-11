package com.server.dto.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.server.domain.Comment;
import com.server.domain.Member;
import com.server.domain.Reply;
import com.server.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemberDTO {
	String id;
	String email;
	String imagePath;
	String nickName;
	Date regisDate;
	Role role;
	List<String> commentId = new ArrayList<String>();
	List<String> comment = new ArrayList<String>();
	
	public SearchMemberDTO(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.imagePath = member.getImagePath();
		this.nickName = member.getNickName();
		this.regisDate = member.getRegisDate();
		this.role = member.getRole();
		for(Comment c : member.getCommentList()) {
			commentId.add(c.getCommentId()+"");
		}
		for(Comment c : member.getCommentList()) {
			comment.add(c.getComment());
		}
	}
	
}
