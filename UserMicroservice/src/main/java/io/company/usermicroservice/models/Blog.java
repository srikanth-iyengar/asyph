package io.company.usermicroservice.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("blog")
public class Blog {
    @PrimaryKey
    @Column("uid")
    @CassandraType(type = Name.TEXT)
    private String uid;

    @Column("title")
    @CassandraType(type = Name.TEXT)
    private String title;

    @Column("created_at")
    @CassandraType(type = Name.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column("updated_at")
    @CassandraType(type = Name.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column("created_by")
    @CassandraType(type = Name.TEXT)
    private String createdBy;

    @Column("content")
    @CassandraType(type = Name.TEXT)
    private String content;

    private Blog(Builder builder) {
        this.uid = UUID.randomUUID().toString();
        this.title = builder.title;
        this.content = builder.content;
        this.createdBy = builder.createdBy;
        this.updatedAt = builder.updatedAt;
        this.createdAt = builder.createdAt;
    }


    public static class Builder {
        public String title;
        public LocalDateTime createdAt, updatedAt;
        public String createdBy, content;
        public Builder() {

        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    /** All args and no args Constructor for spring boot **/

    public Blog() {

    }

    public Blog(String content, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String title, String uid) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.title = title;
        this.uid = uid;
    }

}
