package com.zerock.security;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zerock.persistence.MemberRepository;

import java.util.Arrays;

@Service
@Log
public class ZerockUserService implements UserDetailsService {

	@Autowired
	MemberRepository repo;
/* 
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User sampleUser = new User(username, "{bcrypt}"+encoder.encode("1111"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
        return sampleUser;
    }
    */
/*	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		repo.findById(username).ifPresent(member -> log.info("" + member));
		return null;
	}
*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username)
				.filter(m -> m != null)
				.map(m -> new ZerockSecurityUser(m)).get();
	}
}
