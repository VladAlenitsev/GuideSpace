package com.guidespace.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "createdAt")
    private Date createdAt = new Date(Calendar.getInstance(TimeZone.getTimeZone("EET")).getTime().getTime());

    @Column(name = "changedAt")
    private Date changedAt;

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

}
