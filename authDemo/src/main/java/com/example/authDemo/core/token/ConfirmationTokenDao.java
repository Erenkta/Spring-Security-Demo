package com.example.authDemo.core.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenDao extends JpaRepository<ConfirmationToken, Long> {
	
	Optional<ConfirmationToken> findByToken(String token);

}
