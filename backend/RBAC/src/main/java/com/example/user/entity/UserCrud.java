package com.example.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserCrud {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @JsonProperty(value = "username")
    private String userName;

    @Column(name = "passwd")
    private String password;

    @Column(name = "role")
    private String role;
}
