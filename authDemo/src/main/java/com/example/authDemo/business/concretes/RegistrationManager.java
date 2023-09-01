package com.example.authDemo.business.concretes;

import org.springframework.stereotype.Service;

import com.example.authDemo.business.abstracts.RegistrationService;
import com.example.authDemo.business.abstracts.UserService;
import com.example.authDemo.core.utils.EmailValidator;
import com.example.authDemo.core.utils.UserRole;
import com.example.authDemo.entities.concretes.RegistrationRequest;
import com.example.authDemo.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationManager implements RegistrationService {

	private final EmailValidator emailValidator;
	private final UserService userService;
	
	@Override
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail()); //Test'e sokup true false döndürdük
		if(!isValidEmail) {
			throw new IllegalStateException("email not valid ");// TODO: AOP ile dene
		}
		return userService.singUpUser(
				new User( //Bunun daha kolay bir yolu var mı diye araştır
						request.getFirstName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						UserRole.USER
						)
					);
	}

}
