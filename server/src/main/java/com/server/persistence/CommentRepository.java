package com.server.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
	
	@Query("select max(c.commentId) from Comment c")
	public long getMaxId();
}
