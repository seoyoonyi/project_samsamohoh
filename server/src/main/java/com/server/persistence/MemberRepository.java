package com.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.domain.Member;


public interface MemberRepository extends JpaRepository<Member, String> {

}
