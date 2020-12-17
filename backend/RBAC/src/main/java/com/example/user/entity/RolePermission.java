package com.example.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer rid;

    @Column(name = "permission_id")
    private Integer pid;

    @Column(name = "create_time")
    private Timestamp role_permission_CreateTime;
}
