package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "apps")
public class App {
	
	@Id
	@Column(name = "apps_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "apps_secret")
	private String secret;
	
	@Column(name = "apps_name", unique = true)
	private String name;
	
	@Column(name = "apps_redirect_uri", unique = true)
	private String redirectUri;
	
	@JoinColumn(name = "apps_user_id", nullable = false)
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "app", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<OAuthToken> oauthTokens = new ArrayList<>();
	
}
