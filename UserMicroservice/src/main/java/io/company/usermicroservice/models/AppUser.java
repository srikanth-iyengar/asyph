package io.company.usermicroservice.models;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("app_user")
public class AppUser {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Column("username")
	@CassandraType(type = Name.TEXT)
	private String username;

	@Column("email_id")
	@CassandraType(type = Name.TEXT)
	private String emailId;

	@Column("password")
	@CassandraType(type = Name.TEXT)
	private String password;

	@Column("first_name")
	@CassandraType(type = Name.TEXT)
	private String firstName;

	@Column("last_name")
	@CassandraType(type = Name.TEXT)
	private String lastName;

	@Column("is_locked")
	@CassandraType(type = Name.TEXT)
	private Boolean isLocked;

	@Column("is_enabled")
	@CassandraType(type = Name.TEXT)
	private Boolean isEnabled;

	public AppUser() {

	}

	private AppUser(Builder builder) {
		setEmailId(builder.emailId);
		setUsername(builder.username);
		setPassword(builder.password);
		setFirstName(builder.firstName);
		setLastName(builder.lastName);
		setIsLocked(false);
		setIsEnabled(true);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", emailId=" + emailId + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class Builder {
		public String username, emailId, password, firstName, lastName;

		public Builder() {

		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder emailId(String emailId) {
			this.emailId = emailId;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public AppUser build() {
			return new AppUser(this);
		}
	}

}