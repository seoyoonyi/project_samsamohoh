package com.server.securityconfig;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.server.domain.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
	private static final String SECRET_KEY = "LEEJAEWON";

	public String create(Member member) {
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET_KEY).setSubject(member.getId())
				.claim("nickname", member.getNickName())			
				.claim("password",member.getPassword())
				.claim("role", member.getRole().toString())
				.setIssuer("moleeja app").setIssuedAt(new Date()).setExpiration(expiryDate).compact();
	}

	public Claims validateAndGetPayload(String token) {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			return claims;

	}
	
	public String getMemberId(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public Authentication getAuthnetication(String token) {
		Claims claims = this.validateAndGetPayload(token);
		//User user = new User(claims.getSubject(),claims.get("password").toString(),AuthorityUtils.createAuthorityList(claims.get("role").toString()));
		Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,AuthorityUtils.createAuthorityList(claims.get("role").toString()));
		return authentication;
	}
			
}
