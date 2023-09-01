package com.example.authDemo.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.authDemo.business.abstracts.RegistrationService;
import com.example.authDemo.business.abstracts.UserService;
import com.example.authDemo.core.token.ConfirmationToken;
import com.example.authDemo.core.token.ConfirmationTokenService;
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
	private final ConfirmationTokenService confirmationTokenService;
	
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

	@Override
	@Transactional() //DB işlemlerini Spring Boot'a saldık
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService
				.getToken(token)
				.orElseThrow( ( ) ->new IllegalStateException("token not found")); //Db'de token yoksa
		if(confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed"); //Token varsa ve onaylıysa
		}
		if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) { //Token'in vakti geçmişse
			throw new IllegalStateException("token expired");
		}
		confirmationTokenService.confirmedAt(token);
		userService.enableUser(confirmationToken.getUser().getEmail());
		return "Confirmed";
	}
	

}
