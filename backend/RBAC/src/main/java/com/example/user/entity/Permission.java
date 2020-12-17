package com.example.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String permissionTitle;

    @Column(name = "action")
    private String action;

    @Column(name = "status")
    private Boolean permissionStatus;

    @Column(name = "create_time")
    private Timestamp permission_CreateTime;

    @Column(name = "update_time")
    private Timestamp permission_UpdateTime;
}
