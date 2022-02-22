package com.example.demo.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data

public class EmailChangeForm {
	
    @NotBlank
    @Email
    private String confirmEmail;
    
    @NotBlank
    @Email
    private String email;

}
