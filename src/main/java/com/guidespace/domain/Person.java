package com.guidespace.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;


@Entity
public class Person extends BaseEntity {

    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Length(min = 88, max = 88)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @NotNull
    @Length(min = 44, max = 44)
    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @NotNull
    @Column(name = "EMAIL_ADDRESS", unique = true)
    private String emailAddress;

    @Column(name = "ACTIVE_CERT_LOCATION")
    private String active_cert_location;

    @Column(name = "CERT_EXP_DATE")
    private Date cert_exp_date;

    @Column(name = "USER_ROLE_ID")
    private Integer user_role_id;

    @Column(name = "REGISTERED_EXAM_ID")
    private Integer registered_exam_id;

    @Column(name = "EXAM_REGISTER_DATE")
    private Date exam_register_date;


    public Person() {
    }

    public Person(String username, String passwordHash, String passwordSalt, String emailAddress) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.emailAddress = emailAddress;
        this.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    private void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    private void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getActive_cert_location() {
        return active_cert_location;
    }

    public Date getCert_exp_date() {
        return cert_exp_date;
    }

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public Integer getRegistered_exam_id() {
        return registered_exam_id;
    }

    public Date getExam_register_date() {
        return exam_register_date;
    }


}
