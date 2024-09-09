package com.demo.hrms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hrms.entity.Employee;;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	List<Employee> findByAdminId(Long adminId);

    Optional<Employee> findByEmployeeCode(String employeeCode);
}

