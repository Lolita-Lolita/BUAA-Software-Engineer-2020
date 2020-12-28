package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * User Entity，作为数据库 t_sys_user 的映射，表名和属性均可以自定义，以下仅仅为例子
 */
@Entity
@Table(name = "user")
public @Data class User {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String username;
	
	@Column(name = "passwd")
	private String password;
	
	@Column(name = "role")
	private String role;

}
