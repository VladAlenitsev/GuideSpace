package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "changedAt")
    private Date changeddAt;

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getChangeddAt() {
        return changeddAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setChangeddAt(Date changeddAt) {
        this.changeddAt = changeddAt;
    }

}
