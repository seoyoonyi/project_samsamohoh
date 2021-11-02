package com.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.server.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
