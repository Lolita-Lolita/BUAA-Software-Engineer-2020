package com.example.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "role_id")
    private Integer rid;

    @Column(name = "create_time")
    private Timestamp user_role_CreateTime;
}
