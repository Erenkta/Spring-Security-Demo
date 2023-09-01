package com.example.authDemo.core.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authDemo.business.abstracts.UserService;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{	//Bu Springboot 2 için geçerli ondan dolayı sistemimi downgrade ettim yoksa deprecated bir extend

	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder; //Bean yazdık diye otomatik oluşturulacak
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //geçici olarak disable ettik
			.authorizeRequests()
				.antMatchers("/api/v*/registration/**") //Yıldızlar orası değişebilir demek
				.permitAll() //bu endpointlerden geçen her şey allow olmak zorunda
				.anyRequest()
				.authenticated().and() //buralara gelen her bir request auth olmalı diyoruz
				.formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider()); //Kendi yazdığımız provider'ı verdik
	} 
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder); //Kendi oluşturduğumuz passwordEncoder'ı yollayacağız
		provider.setUserDetailsService(userService); //UserService'imizi buraya verdik
		return provider;
	}
	
	
	
}
