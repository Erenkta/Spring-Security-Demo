package com.example.authDemo.core.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	private final ConfirmationTokenDao confirmationTokenDao;
	
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenDao.save(token);
	}


	public Optional<ConfirmationToken> getToken(String token) {
		return confirmationTokenDao.findByToken(token);
		
	}


	public void confirmedAt(String token) {
		Optional<ConfirmationToken> confirmationToken =  confirmationTokenDao.findByToken(token);
		if(confirmationToken.isPresent()) {
			confirmationToken.get().setConfirmedAt(LocalDateTime.now());
		}
		
	}
}
