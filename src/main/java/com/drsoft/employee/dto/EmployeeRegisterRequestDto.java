package com.drsoft.employee.dto;

import javax.validation.constraints.NotBlank;

import com.drsoft.employee.entity.EmployeeEntity;
import com.drsoft.employee.entity.EmployeeEntity.EmployeeEntityBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmployeeRegisterRequestDto {

	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@NotBlank
	private String age;

}
