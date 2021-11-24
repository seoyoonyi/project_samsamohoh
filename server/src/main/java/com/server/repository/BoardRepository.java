package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.domain.Board;

public interface BoardRepository extends JpaRepository<Board,Long>{
	
}
