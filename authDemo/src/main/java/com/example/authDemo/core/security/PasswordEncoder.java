package com.example.authDemo.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //Configurasyonla alakalı olduğundan dolayı bunu kullanacağız
public class PasswordEncoder {
	
	@Bean //Autowired ile oluşturulsun diye Bean'e ekledik
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); //Basitçe kullanmak için bir obje döndüj diyebiliriz
	}

}
