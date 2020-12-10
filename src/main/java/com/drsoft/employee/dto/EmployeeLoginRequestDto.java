package com.drsoft.employee.dto;

import javax.validation.constraints.NotBlank;

import com.drsoft.employee.dto.EmployeeRegisterRequestDto.EmployeeRegisterRequestDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmployeeLoginRequestDto {
	@NotBlank
	private String name;
	@NotBlank
	private String password;
}
