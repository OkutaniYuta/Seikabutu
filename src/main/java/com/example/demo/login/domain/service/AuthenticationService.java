package com.example.demo.login.domain.service;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	public void change(String email, String password, Collection<? extends GrantedAuthority> authorities) {
		User updated = new User(email, password, authorities);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(updated, updated.getPassword(), updated.getAuthorities()));
	}
}
