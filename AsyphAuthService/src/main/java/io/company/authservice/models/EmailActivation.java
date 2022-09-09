package io.company.authservice.models;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("account_activation")
public class EmailActivation {
	@Column("token")
	@CassandraType(type=Name.TEXT)
	private String token;

	@PrimaryKey
	@Column("username")
	@CassandraType(type=Name.TEXT)
	private String username;

	@Column("generated_at")
	@CassandraType(type=Name.TIMESTAMP)
	private LocalDateTime generatedAt;

	public EmailActivation(String token, String username) {
		this.token = token;
		this.username = username;
		this.generatedAt = LocalDateTime.now();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}

	public String toString() {
		return "{" + username + "," + token + "," + generatedAt + "}";
	}
}
