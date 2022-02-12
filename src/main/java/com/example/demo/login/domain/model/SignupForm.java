package com.example.demo.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	//必須入力
	@NotBlank
	private String userName; // ユーザー名

    //必須入力、メールアドレス形式
    @NotBlank
    @Email
    private String email;
    
    //確認用
    @NotBlank
    @Email
    private String comfirmEmail;

    //必須入力、長さ8から50桁まで、半角英数字のみ
    @NotBlank
    @Length(min = 8, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password; // パスワード

    //パスワード確認
    @NotBlank
    @Length(min = 8, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String passwordConfirmation; // パスワード
    
    private int userId;
    private int userStatus;
    private int requestedAt;
    private int role;



}
