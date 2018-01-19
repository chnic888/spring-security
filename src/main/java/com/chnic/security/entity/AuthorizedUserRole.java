package com.chnic.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorized_user_role")
public class AuthorizedUserRole {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "role")
	private String role;
	
	public AuthorizedUserRole() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
