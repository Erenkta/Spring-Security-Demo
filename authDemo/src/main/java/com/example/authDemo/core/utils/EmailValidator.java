package com.example.authDemo.core.utils;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator  implements Predicate<String>{
	//Validasyon işlemi için oluşturduk güzel ve mantıklı
	@Override
	public boolean test(String t) {
		// TODO: Regex to validate email
		return true;
	}

}
