package io.asyph.problemcontestservice.models;
import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Table("contest")
public class Contest {

	@PrimaryKey
	@Column("contest_id")
	@CassandraType(type = Name.TEXT)
	private String contestId;

	@Column("start_time")
	@CassandraType(type = Name.TIMESTAMP)
	private LocalDateTime startTime;

	@Column("end_time")
	@CassandraType(type = Name.TIMESTAMP)
	private LocalDateTime endTime;

	@Column("contest_name")
	@CassandraType(type = Name.TEXT)
	private String contestName;

	@Column("contest_status")
	@CassandraType(type = Name.TEXT)
	private ContestStatus status;

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	@Column("is_unlisted")
	@CassandraType(type = Name.BOOLEAN)
	private Boolean unlisted;

	@Column("created_by")
	@CassandraType(type = Name.TEXT)
	private String createdBy;

	@Column("scheduler_id")
	@CassandraType(type = Name.TEXT)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String schedulerId;

	private Contest(Builder builder) {
		setContestId();
		setContestName(builder.contestName);
		setEndTime(builder.endTime);
		setStartTime(builder.startTime);
		setStatus("NOT_STARTED");
		setUnlisted(true);
		setCreatedBy(builder.createdBy);
	}

	public Contest() {

	}

	public static class Builder {
		public LocalDateTime startTime, endTime;
		public String contestName;
		public Long registrations;
		private String createdBy;

		public Builder(String createdBy) {
			this.createdBy = createdBy;
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

		public Contest build() {
			return new Contest(this);
		}
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId() {
		this.contestId = Uuids.timeBased().toString();
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public ContestStatus getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = ContestStatus.valueOf(status);
	}

	public void setUnlisted(Boolean val) {
		this.unlisted = val;
	}

	public Boolean getUnlisted() {
		return this.unlisted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
