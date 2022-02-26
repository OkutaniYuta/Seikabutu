package com.example.demo.login.domain.model;

import java.util.Objects;

import javax.validation.constraints.AssertTrue;
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
    
    @AssertTrue(message = "一緒にして")
    public boolean isEmailEqual() {
    	return Objects.equals(email, confirmEmail);
	}
}
