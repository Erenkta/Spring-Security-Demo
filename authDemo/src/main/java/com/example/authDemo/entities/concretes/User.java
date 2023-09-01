package com.example.authDemo.entities.concretes;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.authDemo.core.utils.UserRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User implements UserDetails { //Spring Securty'nin UserDetails interface'ini implemente ettik

	@Id
	@SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
	@GeneratedValue(strategy =GenerationType.SEQUENCE ,generator = "user_sequence")
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING) //Bunun bir enum olduğunu belirtmemiz gerekiyor
	private UserRole userRole;
	//Alttaki ikisine default bir value verdik
	private Boolean locked = false;
	private Boolean enabled = false;
	

	public User(String name, String username, String email, String password, UserRole userRole) {
		//Bunu oluşturma sebebimiz id ,locked,enabled vermeden bir User oluşturmak istiyoruz
		super();
		this.firstName = name;
		this.lastName = username;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name().toString());
		return Collections.singletonList(authority); //singletonList bize tek bir obje tutan bir liste dönüyor
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; //kullanmayacağımızdan dolayı true verdik
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true; //bunu da kullanmayacağımızdan dolayı true verdik
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}
