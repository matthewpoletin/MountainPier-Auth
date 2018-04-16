package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "users_id")
	private UUID id;
	
	@Column(name = "users_username", unique = true)
	private String username;
	
	@Column(name = "users_password")
	private String password;
	
	@Column(name = "salt")
	private String salt;
	
}
