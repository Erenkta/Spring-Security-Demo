package com.example.authDemo.core.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.authDemo.entities.concretes.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken { //Email'e yollayacağımız token için bir entity oluşturduk
	
	@Id
	@SequenceGenerator(name = "confirmation_token_sequence",sequenceName = "confirmation_token_sequence",allocationSize = 1)
	@GeneratedValue(strategy =GenerationType.SEQUENCE ,generator = "confirmation_token_sequence")
	private long id;
	
	@Column(nullable = false) //Özellikle nullable olmasın diye koyduk
	private String token;
	@Column(nullable = false)
	private LocalDateTime createdAt; //Ne zaman oluşturuldu
	
	@Column(nullable = false)
	private LocalDateTime expiresAt; //Ne zaman süresi dolacak
	private LocalDateTime confirmedAt; //Kullanıcı ne zaman tıkladı
	
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "user_id"
	)
	private User user;
	
	
	public ConfirmationToken(User user,String token, LocalDateTime createdAt, LocalDateTime expiredAt) {
		this.user = user;
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiredAt;
	}



	
	

}
