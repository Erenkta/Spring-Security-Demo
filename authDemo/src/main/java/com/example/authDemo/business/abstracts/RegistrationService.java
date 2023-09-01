package com.example.authDemo.business.abstracts;

import com.example.authDemo.entities.concretes.RegistrationRequest;

public interface RegistrationService {
	public String register(RegistrationRequest request);
	public String confirmToken(String token);

}
