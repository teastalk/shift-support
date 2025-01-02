package com.shift.support.controller.employee;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shift.support.entity.Department;
import com.shift.support.entity.Employee;
import com.shift.support.entity.Role;
import com.shift.support.entity.Store;
import com.shift.support.form.employee.StoreForm;
import com.shift.support.mapper.DepartmentMapper;
import com.shift.support.mapper.EmployeeMapper;
import com.shift.support.mapper.RoleMapper;
import com.shift.support.mapper.StoreMapper;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class EmployeeController {
	
	private final RoleMapper roleMapper;
	private final DepartmentMapper departmentMapper;
	private final StoreMapper storeMapper;
	private final EmployeeMapper employeeMapper;
	
	
	
	@ModelAttribute
	public StoreForm setCreateForm() {
		return new StoreForm();
	}
	
	@GetMapping("employee")
	public String index(Model model) {
		
		List<Employee> employees = employeeMapper.getAllWithRoleAndDepartmentAndStore();
		
		model.addAttribute("employees", employees);
		
		return "employee/index";
	}

//	従業員登録画面表示
	@GetMapping("employee/create")
	public String create(Model model) {
		
//		役職一覧取得
		List<Role> roles = roleMapper.getAll();
		model.addAttribute("roles", roles);

//		部門一覧取得
		List<Department> departments = departmentMapper.getAll();
		model.addAttribute("departments", departments);
		
//		店舗一覧取得
		List<Store> stores = storeMapper.getAll();
		model.addAttribute("stores", stores);
		
		
		return "employee/create";
	}
	
//	従業員登録実行
	@PostMapping("employee/store")
	public String store(@Validated StoreForm form,
			BindingResult result, Model model) {
		try {			
			if(result.hasErrors()) {
//			入力チェックNG
				
//			役職一覧取得
				List<Role> roles = roleMapper.getAll();
				model.addAttribute("roles", roles);
				
//			部門一覧取得
				List<Department> departments = departmentMapper.getAll();
				model.addAttribute("departments", departments);
				
//			店舗一覧取得
				List<Store> stores = storeMapper.getAll();
				model.addAttribute("stores", stores);
				
				return "/employee/create";
			}
			
			int roleId = form.getRole();
			int departmentId = form.getDepartment();
			String storeCode = form.getStore();
			
//			役職の存在チェック
			Role role = roleMapper.getById(roleId); 	
			if(role == null) {
				throw new Exception();
			}
			
//			部門の存在チェック
			Department department = departmentMapper.getById(departmentId);
			if(department == null) {
				throw new Exception();
			}
			
//			店舗の存在チェック
			Store store = storeMapper.getByStoreCode(storeCode);
			if(store == null) {
				throw new Exception();
			}
			
			int length = 10;
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        Random random = new Random();

	        StringBuilder stringBuilder = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            stringBuilder.append(characters.charAt(index));
	        }

	        String employeeCode = stringBuilder.toString();
			
			
			employeeMapper.create(
						employeeCode,
						roleId,
						storeCode,
						departmentId,
						form.getFirstName(),
						form.getLastName(),
						form.getPassword(),
						form.getPassword(), //誕生日文字列に修正
						form.getWorkPerWeek(),
						form.getWorkPerDay()
					);
			
			
			return "redirect:/employee";
		}catch(Exception e) {
			return "index";
		}
		
	}
	
}



