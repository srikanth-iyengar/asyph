package io.asyph.problemcontestservice.models;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
public class ProblemCache {

    @Column(value = "points")
    @CassandraType(type = Name.INT)
    private Integer points;

    @Column(value = "wrong_submissions")
    @CassandraType(type = Name.INT)
    private Integer wrongSubmission;

    @Column(value = "is_accepted")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean isAccepted;

    public ProblemCache() {
        this.points = 0;
        this.wrongSubmission = 0;
        isAccepted = false;
    }

    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public Integer getWrongSubmission() {
        return wrongSubmission;
    }
    public void setWrongSubmission(Integer wrongSubmission) {
        this.wrongSubmission = wrongSubmission;
    }
    public Boolean getIsAccepted() {
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
