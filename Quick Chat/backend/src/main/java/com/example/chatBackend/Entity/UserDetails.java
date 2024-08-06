package com.example.chatBackend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    private String userName;

    private String nickname;

    @Column(nullable = false)
    private String email;

    private String about;

    private String dateOfBirth;

    private String country;

    public UserDetails() {
        // Default constructor required by JPA
    }

    public UserDetails(String userName, String nickname, String email, String about, String dateOfBirth, String country) {
        this.userName = userName;
        this.nickname = nickname;
        this.email = email;
        this.about = about;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
