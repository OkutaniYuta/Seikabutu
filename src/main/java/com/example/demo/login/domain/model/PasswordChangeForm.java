package com.example.demo.login.domain.model;

import java.util.Objects;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data

public class PasswordChangeForm {
	//パスワード確認
	@NotBlank
	@Length(min = 8, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String confirmPassword;

	//必須入力、長さ8から50桁まで、半角英数字のみ
	@NotBlank
	@Length(min = 8, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String nowPassword;

	//必須入力、長さ8から50桁まで、半角英数字のみ
	@NotBlank
	@Length(min = 8, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String newPassword;
	
	@AssertTrue(message = "一緒にして")
	public boolean isPasswordEqual() {
    	return Objects.equals(newPassword, confirmPassword);
	}
}
