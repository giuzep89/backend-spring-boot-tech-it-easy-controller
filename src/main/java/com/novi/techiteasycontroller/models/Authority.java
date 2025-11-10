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

    // Getters and setters

    // -------> Setters are best avoided in an authority model class,
    // as it might cause breaking its tight relationship with the user class

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}