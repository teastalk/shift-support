package com.shift.support.form;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotBlank(message = "従業員番号を入力してください。")
	private String perCd;
	
	@NotBlank(message = "パスワードを入力してください。")
	private String password;
}
