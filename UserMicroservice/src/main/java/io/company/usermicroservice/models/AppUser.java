package io.company.usermicroservice.models;

import java.util.Collection;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("app_user")
public class AppUser implements UserDetails {

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", emailId=" + emailId + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

}
