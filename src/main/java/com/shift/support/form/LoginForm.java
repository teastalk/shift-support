package com.shift.support.form;



import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotNull(message = "従業員番号を入力してください。")
	private String perCd;
	
	@NotNull(message = "パスワードを入力してください。")
	private String password;
}
