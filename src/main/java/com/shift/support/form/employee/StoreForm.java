package com.shift.support.form.employee;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreForm {
	
	@NotBlank(message = "苗字を入力してください")
	private String firstName;
	
	@NotBlank(message = "名前を入力してください")
	private String lastName;
	
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	@Min(message = "役職を選択してください", value = 0)
	private int role;
	
	@Min(message = "部門を選択してください", value = 0)
	private int department;
	
	@NotBlank(message = "店舗を選択してください")
	private String store;
	
	@NotNull(message = "希望勤務日数を入力してください")
	private int workPerWeek;

	@NotNull(message = "希望勤務時間を入力してください")
	private int workPerDay;
}
