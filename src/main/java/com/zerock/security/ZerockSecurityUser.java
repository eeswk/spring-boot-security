package com.zerock.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.zerock.domain.Member;
import com.zerock.domain.MemberRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZerockSecurityUser extends User{
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	private Member member;
	
	public ZerockSecurityUser(Member member) {
		super(member.getUid(), "{bcrypt}"+member.getUpw(), makeGrantedAthority(member.getRoles()));
		this.member = member;
	}

	private static Collection<? extends GrantedAuthority> makeGrantedAthority(List<MemberRole> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}
/*
	public ZerockSecurityUser(
			String username,
			String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	*/
}
