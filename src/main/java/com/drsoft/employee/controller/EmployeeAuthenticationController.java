package com.drsoft.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drsoft.employee.config.Response;
import com.drsoft.employee.config.SuccessCode;
import com.drsoft.employee.dto.EmployeeLoginRequestDto;
import com.drsoft.employee.dto.EmployeeLoginResponse;
import com.drsoft.employee.dto.EmployeeRegisterRequestDto;
import com.drsoft.employee.entity.EmployeeEntity;
import com.drsoft.employee.service.EmployeeAuthService;

@RestController
@RequestMapping("/employee")

public class EmployeeAuthenticationController {
	@Autowired
	EmployeeAuthService employeeAuthService;

	@GetMapping("/findall")
	public Response<List<EmployeeEntity>> findall() {
		List<EmployeeEntity> listEmployees = employeeAuthService.findAll();
		return Response.createSuccessResponse(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getDescription(),
				listEmployees);
	}

	@PostMapping("/register")
	public <T> Response<T> register(@RequestBody EmployeeRegisterRequestDto employeedto) {
		employeeAuthService.register(employeedto);
		return Response.createSuccessResponse(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getDescription());
	}
	@PostMapping("/login")
	public Response<EmployeeLoginResponse> login(@RequestBody EmployeeLoginRequestDto employeedto) {
		EmployeeLoginResponse loginRes=employeeAuthService.login(employeedto);
		return Response.createSuccessResponse(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getDescription(),loginRes);
	}
}
