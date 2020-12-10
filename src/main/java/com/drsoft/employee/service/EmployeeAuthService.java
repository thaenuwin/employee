package com.drsoft.employee.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.drsoft.employee.config.Response;
import com.drsoft.employee.dto.EmployeeLoginRequestDto;
import com.drsoft.employee.dto.EmployeeLoginResponse;
import com.drsoft.employee.dto.EmployeeRegisterRequestDto;
import com.drsoft.employee.entity.EmployeeEntity;

public interface EmployeeAuthService {
	List<EmployeeEntity> findAll();

	int register(EmployeeRegisterRequestDto employeedto);

	EmployeeLoginResponse login(EmployeeLoginRequestDto employeedto);
}
