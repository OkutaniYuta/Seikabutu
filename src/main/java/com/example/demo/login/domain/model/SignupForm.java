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

    //必須入力、メールアドレス形式
    @NotBlank(message = "{require_check}")
    @Email(message = "{email_check}")
    private String mailAddress;

    //必須入力、長さ4から100桁まで、半角英数字のみ
    @NotBlank(message = "{require_check}")
    @Length(message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern_check}")
    private String password; // パスワード

    //必須入力
    @NotBlank(message = "{require_check}")
    private String userName; // ユーザー名


}
