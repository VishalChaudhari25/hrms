package com.demo.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hrms.dto.EmployeeDto;
import com.demo.hrms.entity.Admin;
import com.demo.hrms.entity.Employee;
import com.demo.hrms.repository.AdminRepository;
import com.demo.hrms.repository.EmployeeRepository;
import com.demo.hrms.util.EmployeeUtil;

@Service
public class EmployeeServices {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeUtil employeeUtil;

	public Employee registerEmployee(EmployeeDto employeeDto, Long adminId) {
	    employeeUtil.validateEmployeeDto(employeeDto);

	    Optional<Admin> admin = adminRepository.findById(adminId);
	    if (!admin.isPresent()) {
	        throw new RuntimeException("Admin not found");
	    }

	    if (employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode()).isPresent()) {
	        throw new RuntimeException("User already exists");
	    }

	    Employee employee = employeeUtil.mapToEntity(employeeDto);
	    employee.setAdmin(admin.get());
	    employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

	    // Validate the employee before saving
	    employeeUtil.validateEmployee(employee);

	    return employeeRepository.save(employee);
	}
	
	
	
	
	
	//Login Employee Function 
	public Employee loginEmployee(EmployeeDto employeeDto) {
		Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode());
		if (employee.isPresent() && passwordEncoder.matches(employeeDto.getPassword(), employee.get().getPassword())) {
			return employee.get();
		}
		throw new RuntimeException("Invalid employee code or password");
	}

	
	//Get the Employees by admin Id
	public List<Employee> getEmployeesByAdminId(Long adminId) {
		return employeeRepository.findByAdminId(adminId);
	}
	
	
	//Get the Employee by EmployeeCode
	public Employee getEmployeeByCode(String employeeCode) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
		if (optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} else {
			throw new RuntimeException("Employee not found with code " + employeeCode);
		}
	}

	
	//Update the EmployeeDetails
	public Employee updateEmployee(EmployeeDto employeeDto, String employeeCode) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employeeUtil.updateEmployeeFromDto(employee, employeeDto, passwordEncoder);
			return employeeRepository.save(employee);
		} else {
			throw new RuntimeException("Employee not found with code " + employeeCode);
		}
	}

	
	
	//Delete the Employee
	public void deleteEmployee(String employeeCode) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
		if (optionalEmployee.isPresent()) {
			employeeRepository.delete(optionalEmployee.get());
		} else {
			throw new RuntimeException("Employee not found with code " + employeeCode);
		}
	}
}

//public Employee registerEmployee(EmployeeDto employeeDto, Long adminId) {
//	Optional<Admin> admin = adminRepository.findById(adminId);
//	if (!admin.isPresent()) {
//		throw new RuntimeException("Admin not found");
//	}
//
//	if (employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode()).isPresent()) {
//		throw new RuntimeException("User already exists");
//	}
//
//	if (employeeDto.getEmployeeCode() == null || employeeDto.getEmployeeCode().trim().isEmpty()
//			|| employeeDto.getFirstName() == null || employeeDto.getFirstName().trim().isEmpty()
//			|| employeeDto.getLastName() == null || employeeDto.getLastName().trim().isEmpty()
//			|| employeeDto.getEmail() == null || employeeDto.getEmail().trim().isEmpty()
//			|| employeeDto.getPassword() == null || employeeDto.getPassword().trim().isEmpty()
//			|| employeeDto.getCity() == null || employeeDto.getCity().trim().isEmpty()
//			|| employeeDto.getAge() == null || employeeDto.getAge().trim().isEmpty()) {
//		throw new RuntimeException("All fields are required and cannot be empty");
//	}
//	Employee employee = new Employee();
//	employee.setEmployeeCode(employeeDto.getEmployeeCode());
//	employee.setFirstName(employeeDto.getFirstName());
//	employee.setLastName(employeeDto.getLastName());
//	employee.setEmail(employeeDto.getEmail());
//	employee.setCity(employeeDto.getCity());
//	employee.setAge(employeeDto.getAge());
//	employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
//	employee.setAdmin(admin.get());
//
//	return employeeRepository.save(employee);
//}

//public Employee loginEmployee(EmployeeDto employeeDto) {
//	Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode());
//	if (employee.isPresent() && passwordEncoder.matches(employeeDto.getPassword(), employee.get().getPassword())) {
//		return employee.get();
//	}
//	throw new RuntimeException("Invalid employee code or password");
//}
//
//public List<Employee> getEmployeesByAdminId(Long adminId) {
//	return employeeRepository.findByAdminId(adminId);
//}
//
//public Employee getEmployeeByCode(String employeeCode) {
//	Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
//	if (optionalEmployee.isPresent()) {
//		return optionalEmployee.get();
//	} else {
//		throw new RuntimeException("Employee not found with code " + employeeCode);
//	}
//}

//public Employee updateEmployee(EmployeeDto employeeDto, String employeeCode) {
//	Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
//	if (optionalEmployee.isPresent()) {
//		Employee employee = optionalEmployee.get();
//		employee.setFirstName(employeeDto.getFirstName());
//		employee.setLastName(employeeDto.getLastName());
//		employee.setEmail(employeeDto.getEmail());
//		employee.setCity(employeeDto.getCity());
//		employee.setAge(employeeDto.getAge());
//		if (employeeDto.getPassword() != null && !employeeDto.getPassword().trim().isEmpty()) {
//			employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
//		}
//		return employeeRepository.save(employee);
//	} else {
//		throw new RuntimeException("Employee not found with code " + employeeCode);
//	}
//}

//public void deleteEmployee(String employeeCode) {
//	Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeCode);
//	if (optionalEmployee.isPresent()) {
//		employeeRepository.delete(optionalEmployee.get());
//	} else {
//		throw new RuntimeException("Employee not found with code " + employeeCode);
//	}
//}
