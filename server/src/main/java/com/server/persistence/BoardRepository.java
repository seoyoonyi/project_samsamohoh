package com.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{
	
}
