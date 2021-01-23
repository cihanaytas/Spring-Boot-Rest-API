package com.godoro.springRent.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.data.repository.CustomerRepository;

 

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password;
		String role;

		if(username.equals("admin")) {
			password = "78900";
			role = "ADMIN";
		}
		
		else {
		Customer customer = customerRepository.findByUsername(username);
		password=customer.getPassword();
		role="CUSTOMER";

		}
		
		return User
				.withUsername(username)
				.password(passwordEncoder.encode(password))
				.roles(role)
				.build();
	}

	
}
