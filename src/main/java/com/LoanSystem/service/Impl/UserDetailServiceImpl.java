package com.LoanSystem.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.LoanSystem.model.Role;
import com.LoanSystem.model.User;
import com.LoanSystem.repository.UserJpaRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserJpaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("Load user by name " + userId);
		User user = userRepository.findByUserId(userId);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		 for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
		String encode = encodePassword(user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getUserId(), encode, grantedAuthorities);

	}

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
