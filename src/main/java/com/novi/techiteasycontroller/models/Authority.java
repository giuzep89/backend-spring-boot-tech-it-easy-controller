package com.novi.techiteasycontroller.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    // Constructors
    public Authority() {}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private User user;

    // Getters and setters

    // -------> Setters for the authority are best avoided,
    // as it might cause breaking its tight relationship with the user class
    // but setters for the associated User are ok

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}