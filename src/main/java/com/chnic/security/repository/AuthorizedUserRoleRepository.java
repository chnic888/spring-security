package com.chnic.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chnic.security.entity.AuthorizedUserRole;

@Repository
public interface AuthorizedUserRoleRepository extends JpaRepository<AuthorizedUserRole, Integer>  {

	List<AuthorizedUserRole> findByClientId(String clientId);
}
