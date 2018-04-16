package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;

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
	
}
