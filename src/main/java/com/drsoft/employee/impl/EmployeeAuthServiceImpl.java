package com.drsoft.employee.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.drsoft.employee.config.ErrorCode;
import com.drsoft.employee.dto.EmployeeLoginRequestDto;
import com.drsoft.employee.dto.EmployeeLoginResponse;
import com.drsoft.employee.dto.EmployeeRegisterRequestDto;
import com.drsoft.employee.entity.EmployeeEntity;
import com.drsoft.employee.exception.AlreadyExistException;
import com.drsoft.employee.exception.EntityNotFoundException;
import com.drsoft.employee.exception.PasswordWrongException;
import com.drsoft.employee.repository.EmployeeRepo;
import com.drsoft.employee.security.JwtTokenUtil;
import com.drsoft.employee.service.EmployeeAuthService;
import com.drsoft.employee.util.ValidatorUtil;

@Service
public class EmployeeAuthServiceImpl implements EmployeeAuthService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Transactional
	@Override
	public List<EmployeeEntity> findAll() {
		List<EmployeeEntity> listEmployees = (List<EmployeeEntity>) employeeRepo.findAll();
		List<EmployeeEntity> response=listEmployees.stream().map(e ->{
			return EmployeeEntity.toResponse(e);
		}).collect(Collectors.toList());
		return response;
	}
	
	@Transactional
	@Override
	public int register(EmployeeRegisterRequestDto employeedto) {
		ValidatorUtil.validate(employeedto);
		EmployeeEntity employeeDetails = employeeRepo.findByName(employeedto.getName());
		if (employeeDetails == null) {
			EmployeeEntity employeeEntity = EmployeeEntity.builder().name(employeedto.getName())
					.password(bcryptPasswordEncoder.encode(employeedto.getPassword())).age(employeedto.getAge())
					.build();
			employeeRepo.save(employeeEntity);
		} else
			throw new AlreadyExistException(ErrorCode.ERROR_ALREADY_EXIST.getCode(),
					ErrorCode.ERROR_ALREADY_EXIST.getDescription());

		return 1;
	}
	
	@Transactional
	@Override
	public EmployeeLoginResponse login(EmployeeLoginRequestDto employeedto) {
		ValidatorUtil.validate(employeedto);
		EmployeeEntity employeeDetails = employeeRepo.findByName(employeedto.getName());
		EmployeeLoginResponse empResponse;
		if (employeeDetails != null) {
			Boolean result = bcryptPasswordEncoder.matches(employeedto.getPassword().trim(),
					employeeDetails.getPassword());
			if (result) {
				String token = jwtTokenUtil.generateToken(employeeDetails);
				empResponse=EmployeeLoginResponse.builder()
				.token(token)
				.employee(EmployeeEntity.toResponse(employeeDetails))
				.build();
			} else
				throw new PasswordWrongException(ErrorCode.ERROR_INCORRECT_PASSWORD.getCode(),
						ErrorCode.ERROR_INCORRECT_PASSWORD.getDescription());
		} else
			throw new EntityNotFoundException(ErrorCode.ERROR_ENTITY_NOT_FOUND.getCode(),
					ErrorCode.ERROR_ENTITY_NOT_FOUND.getDescription());
		return empResponse;
	}

}
