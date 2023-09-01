package com.example.authDemo.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.authDemo.entities.concretes.User;

@Repository
@Transactional(readOnly = true)
public interface UserDao {
	Optional<User> findByEmail(String email);

}
