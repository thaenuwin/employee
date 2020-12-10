package com.drsoft.employee.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id")
	private Long id;
	private String name;
	private String password;
	private String age;

	public static EmployeeEntity toResponse(EmployeeEntity employee) {
		return EmployeeEntity.builder().id(employee.getId()).name(employee.getName()).age(employee.getAge()).build();
	}
}
