package com.example.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "role")
public class Role {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String roleName;

    @Column(name = "status")
    private Boolean roleStatus;

    @Column(name = "create_time")
    private Timestamp role_CreateTime;

    @Column(name = "update_time")
    private Timestamp role_UpdateTime;
}
