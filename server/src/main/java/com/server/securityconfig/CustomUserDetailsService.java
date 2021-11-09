package com.server.securityconfig;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.domain.Member;
import com.server.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		Optional<Member> option = memberRepo.findById(username);
		
		if(option.isPresent()) {
			return new SecurityUser(option.get());
		}else {
			throw new UsernameNotFoundException("해당 사용자 없음");
			
		}
		
		
	}

}
