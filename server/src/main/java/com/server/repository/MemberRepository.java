package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
