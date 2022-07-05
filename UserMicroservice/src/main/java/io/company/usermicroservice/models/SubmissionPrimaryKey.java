package io.company.usermicroservice.models;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;


@PrimaryKeyClass
public class SubmissionPrimaryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2476442327647806151L;

	@PrimaryKeyColumn(name = "submissionId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String id;

	@PrimaryKeyColumn(name = "submittedBy", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
