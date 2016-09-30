package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;


@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "createdAt")
    private Timestamp createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "changedAt")
    private Timestamp changeddAt;

    @Column(name = "changedBy")
    private String changedBy;

    public Long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getChangeddAt() {
        return changeddAt;
    }

    public String getChangedBy() {
        return changedBy;
    }
}
