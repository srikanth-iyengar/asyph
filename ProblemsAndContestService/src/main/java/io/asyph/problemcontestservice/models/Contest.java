package io.asyph.problemcontestservice.models;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("contest")
public class Contest {

	@PrimaryKey
	@Column("contest_id")
	@CassandraType(type = Name.BIGINT)
	private Long contestId;

	@Column("start_time")
	@CassandraType(type = Name.TIMESTAMP)
	private LocalDateTime startTime;

	@Column("end_time")
	@CassandraType(type = Name.TIMESTAMP)
	private LocalDateTime endTime;

	@Column("contest_name")
	@CassandraType(type = Name.TEXT)
	private String contestName;

	@Column("registrations")
	@CassandraType(type = Name.BIGINT)
	private Long registrations;

	public static class Builder {
		public LocalDateTime startTime, endTime;
		public String contestName;
		public Long registrations;

		public Builder() {
			
		}

		public Builder startTime(LocalDateTime startTime) {
			this.startTime = startTime;
			return this;
		}

		public Builder endTime(LocalDateTime endTime) {
			this.endTime = endTime;
			return this;
		}

		public Builder contestName(String contestName) {
			this.contestName = contestName;
			return this;
		}
	}

}
