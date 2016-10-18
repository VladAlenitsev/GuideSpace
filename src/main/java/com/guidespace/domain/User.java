package com.guidespace.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;



@Entity(name = "Person")
public class User extends BaseEntity {

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

    public User() {
    }


    public User(String username, String passwordHash, String passwordSalt, String emailAddress) {
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
}
