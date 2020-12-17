package com.example.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String userName;

    @Column(name = "passwd")
    private String password;

    @Column(name = "status")
    private Boolean userStatus;

    @Column(name = "create_time")
    private Timestamp user_CreateTime;

    @Column(name = "update_time")
    private Timestamp user_UpdateTime;
}
