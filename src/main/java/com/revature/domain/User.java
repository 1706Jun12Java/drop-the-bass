package com.revature.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "USERS_TABLE")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_ID_Seq")
	@SequenceGenerator(allocationSize = 1, name = "User_ID_Seq", sequenceName = "User_ID_Seq")
	@Column(name = "USER_ID")
	private int id;
	@Column(name = "USERNAME", unique = true)
	private String username;
	@Column(name = "USERPASS")
	private String password;
	@Column(name = "ACCOUNTTYPE")
	private int accountType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
}
