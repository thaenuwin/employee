package com.drsoft.employee;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.drsoft.employee.config.ErrorCode;
import com.drsoft.employee.dto.EmployeeLoginRequestDto;
import com.drsoft.employee.dto.EmployeeLoginResponse;
import com.drsoft.employee.entity.EmployeeEntity;
import com.drsoft.employee.exception.EntityNotFoundException;
import com.drsoft.employee.exception.PasswordWrongException;
import com.drsoft.employee.impl.EmployeeAuthServiceImpl;
import com.drsoft.employee.repository.EmployeeRepo;
import com.drsoft.employee.util.ValidationErrors;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeApplication.class)
class EmployeeApplicationTests {

	@Autowired
	EmployeeAuthServiceImpl employeeAuthServiceimpl;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Test
	@Transactional
	void should_Success_Login() {
		EmployeeLoginRequestDto req=EmployeeLoginRequestDto.builder()
				.name("thae2")
				.password("thaethae1")
				.build();
		EmployeeEntity employee=employeeRepo.findByName("thae2");
		EmployeeLoginResponse res=employeeAuthServiceimpl.login(req);
		assertThat(res.getToken()).isNotNull();
		assertThat(res.getEmployee().getId()).isEqualTo(employee.getId());
		assertThat(res.getEmployee().getName()).isEqualTo(employee.getName());
	}
	
	 @Test
	    @Transactional
	    void shouldThrowException_WhenLogin_UserNameNotFound() {
	        try {
	        	EmployeeLoginRequestDto req=EmployeeLoginRequestDto.builder()
	    				.name("thae")
	    				.password("thaethae")
	    				.build();
	    		EmployeeLoginResponse res=employeeAuthServiceimpl.login(req);
	        } catch (EntityNotFoundException e) {
	            assertThat(e.getCode()).isEqualTo(ErrorCode.ERROR_ENTITY_NOT_FOUND.getCode());
	            assertThat(e.getMessage()).isEqualTo(ErrorCode.ERROR_ENTITY_NOT_FOUND.getDescription());
	        }
	    }
	 	@Test
	    @Transactional
	    void shouldThrowException_WhenLogin_PasswordWrong() {
	        try {
	        	EmployeeLoginRequestDto req=EmployeeLoginRequestDto.builder()
	    				.name("thae2")
	    				.password("thae")
	    				.build();
	    		EmployeeLoginResponse res=employeeAuthServiceimpl.login(req);
	        } catch (PasswordWrongException e) {
	            assertThat(e.getCode()).isEqualTo(ErrorCode.ERROR_INCORRECT_PASSWORD.getCode());
	            assertThat(e.getMessage()).isEqualTo(ErrorCode.ERROR_INCORRECT_PASSWORD.getDescription());
	        }
	    }
	 	 @Test
	     @Transactional
	     public void login_Validation_RequiredFields_Null() {
	         try {
	        	 EmployeeLoginRequestDto req=EmployeeLoginRequestDto.builder()
		    				.name("")
		    				.password("")
		    				.build();
		    		EmployeeLoginResponse res=employeeAuthServiceimpl.login(req);
	         } catch (ConstraintViolationException e) {
	            Set violations = e.getConstraintViolations();
	            ConstraintViolationAssert.assertViolations(violations).hasViolation("name", ValidationErrors.MSG_MUST_NOT_BLANK);
	            ConstraintViolationAssert.assertViolations(violations).hasViolation("password", ValidationErrors.MSG_MUST_NOT_BLANK);
	         }
	     }

}
