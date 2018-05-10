package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
	
	@Column(name = "users_salt")
	private String salt;
	
	@Enumerated
	@Column(name = "users_role", nullable = false, columnDefinition = "smallint")
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<App> apps = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<UserToken> userTokens = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<OAuthToken> oAuthTokens = new ArrayList<>();
	
}
