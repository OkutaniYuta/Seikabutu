package com.example.demo.login.domain.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
	private String userName;
	private String password;
	private String mailAddress;
	private String role;
}