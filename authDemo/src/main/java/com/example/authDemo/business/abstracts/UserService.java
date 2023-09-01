package com.example.authDemo.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.authDemo.entities.concretes.User;

public interface UserService extends UserDetailsService {
	
	public String singUpUser(User user); //Kullanıcı kayıt işlemleri olan fonksiyon

	public void enableUser(String email);

}
