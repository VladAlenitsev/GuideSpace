package com.guidespace.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.*;


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

    @OneToMany
    @JoinColumn(name = "EXAM_RESULT_ID")
    private List<ExamResult> results = new ArrayList<ExamResult>();

    public Person() {
    }

    public Person(String username, String passwordHash, String passwordSalt, String emailAddress) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
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

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setActive_cert_location(String active_cert_location) {
        this.active_cert_location = active_cert_location;
    }

    public void setCert_exp_date(Date cert_exp_date) {
        this.cert_exp_date = cert_exp_date;
    }

    public void setRegistered_exam_id(Integer registered_exam_id) {
        this.registered_exam_id = registered_exam_id;
    }

    public void setExam_register_date(Date exam_register_date) {
        this.exam_register_date = exam_register_date;
    }

    public List<ExamResult> getResults() {
        return results;
    }

    public void setResults(List<ExamResult> results) {
        this.results = results;
    }


}
