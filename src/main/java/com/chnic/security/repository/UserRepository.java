package com.chnic.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chnic.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findOneByUserName(String userName);
}
