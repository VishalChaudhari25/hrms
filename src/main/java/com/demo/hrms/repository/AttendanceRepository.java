package com.demo.hrms.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hrms.entity.Attendance;
import com.demo.hrms.entity.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByEmployee_EmployeeCodeOrderByDateDesc(String employeeCode);

	// Method to find all attendance records where outTime is null
	List<Attendance> findByOutTimeIsNull();

	Optional<Attendance> findByEmployeeAndDateAndOutTimeIsNull(Employee employee, java.sql.Date date);

	boolean existsByEmployeeAndDateAndOutTimeIsNull(Employee employee, java.sql.Date date);

	Optional<Attendance> findByEmployee_EmployeeCodeAndDate(String employeeCode, Date date);

	Boolean findIsCheckedInByEmployee_EmployeeCode(String employeeCode);
}
