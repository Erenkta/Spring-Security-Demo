package com.example.authDemo.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authDemo.business.abstracts.UserService;
import com.example.authDemo.dataAccess.abstracts.UserDao;
import com.example.authDemo.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
	
	@Autowired
	private final UserDao userDao;
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userDao.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username))); 
		// String.format kullanarak C'den de bildiğin üzere %s yerine email'i koyduk 
	}

	@Override
	public String singUpUser(User user) {
		Optional<User> inDb = userDao.findByEmail(user.getEmail());
		if(inDb.isPresent()) {
			throw new IllegalStateException("email already taken"); //Kullanıcı daha önceden oluşturulmuş iste hata döndürdü TODO: Aop ile dene
		}
		String encodedPassword = passwordEncoder
				.encode(user.getPassword()); //Eğer kullanıcı daha önce oluşmamıssa şifresini BCRYPT ettik
		user.setPassword(encodedPassword); //şifresini pasladık 
		
		userDao.save(user);
		
		
		// TODO: send confirmation token
		return "it works";
	}

}
