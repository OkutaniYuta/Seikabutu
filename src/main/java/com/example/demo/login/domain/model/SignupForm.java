package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	//必須入力
	@NotBlank
	private String userName; // ユーザー名

    //必須入力、メールアドレス形式
    @NotBlank
    @Email
    private String mailAddress;

    //必須入力、長さ8から50桁まで、半角英数字のみ
    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password; // パスワード

    //パスワード確認
    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String passwordConfirmation; // パスワード



}
