package com.example.authDemo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authDemo.business.abstracts.UserService;
import com.example.authDemo.dataAccess.abstracts.UserDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
	
	@Autowired
	private final UserDao userDao;
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userDao.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username))); 
		// String.format kullanarak C'den de bildiğin üzere %s yerine email'i koyduk 
	}

}
