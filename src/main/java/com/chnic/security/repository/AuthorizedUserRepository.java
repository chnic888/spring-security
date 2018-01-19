package com.chnic.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chnic.security.entity.AuthorizedUser;

@Repository
public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, Integer> {

	AuthorizedUser findOneByClientId(String clientId);
}
