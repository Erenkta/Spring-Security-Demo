package com.example.authDemo.business.concretes;

import org.springframework.stereotype.Service;

import com.example.authDemo.business.abstracts.RegistrationService;
import com.example.authDemo.entities.concretes.RegistrationRequest;

@Service
public class RegistrationManager implements RegistrationService {

	@Override
	public String register(RegistrationRequest request) {
		// TODO Auto-generated method stub
		return "It Works";
	}

}
