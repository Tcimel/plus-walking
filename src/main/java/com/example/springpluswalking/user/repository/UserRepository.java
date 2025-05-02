package com.example.springpluswalking.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springpluswalking.user.entity.User;
import com.example.springpluswalking.user.exception.UserErrorCode;
import com.example.springpluswalking.user.exception.UserException;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
	default User findByEmailOrElseThrow(String email){
		return findByEmail(email).orElseThrow(()-> new UserException(UserErrorCode.USER_NOT_FOUND));
	}
}
