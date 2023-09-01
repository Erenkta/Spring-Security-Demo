package com.example.authDemo.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.authDemo.entities.concretes.User;

@Repository
@Transactional(readOnly = true)
public interface UserDao extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

}
