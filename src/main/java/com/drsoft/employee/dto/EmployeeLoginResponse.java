package com.drsoft.employee.dto;

import com.drsoft.employee.dto.EmployeeRegisterRequestDto.EmployeeRegisterRequestDtoBuilder;
import com.drsoft.employee.entity.EmployeeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmployeeLoginResponse {

	private String token;
	private EmployeeEntity employee;
}
