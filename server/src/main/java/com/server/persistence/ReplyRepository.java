package com.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.domain.Reply;
@Service
public interface ReplyRepository extends JpaRepository<Reply,Long> {

}
