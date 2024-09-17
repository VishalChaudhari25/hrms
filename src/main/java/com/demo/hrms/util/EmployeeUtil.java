package com.demo.hrms.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hrms.dto.EmployeeDto;
import com.demo.hrms.entity.Employee;
import com.demo.hrms.exception.InvalidEmployeeException;

@Service
public class EmployeeUtil {

	private ModelMapper modelMapper;

	public EmployeeUtil(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

	}

	public Employee mapToEntity(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		if (employeeDto.getDateOfBirth() != null) {
	        LocalDate dateOfBirth = LocalDate.parse(employeeDto.getDateOfBirth().toString());
	        employee.setDateOfBirth(dateOfBirth);
	    }
	    if (employeeDto.getDateOfJoining() != null) {
	        LocalDate dateOfJoining = LocalDate.parse(employeeDto.getDateOfJoining().toString());
	        employee.setDateOfJoining(dateOfJoining);
	    }
		return employee;
	}

	public void validateEmployee(Employee employee) {
	    if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
	        throw new RuntimeException("First name is required");
	    }
	    if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
	        throw new RuntimeException("Last name is required");
	    }
	    if (employee.getPersonalEmail() == null || employee.getPersonalEmail().trim().isEmpty()) {
	        throw new RuntimeException("Personal email is required");
	    }
	    if (employee.getLocation() == null || employee.getLocation().trim().isEmpty()) {
	        throw new RuntimeException("Location is required");
	    }
	    if (employee.getAge() == null || employee.getAge().trim().isEmpty()) {
	        throw new RuntimeException("Age is required");
	    }
	    if (employee.getDateOfBirth() == null) {
	        throw new RuntimeException("Date of birth is required");
	    }
	    if (employee.getDateOfJoining() == null) {
	        throw new RuntimeException("Date of joining is required");
	    }

	    if (employee.getDateOfBirth() != null && !isValidDate(employee.getDateOfBirth().toString())) {
	        throw new InvalidEmployeeException("Invalid date of birth format. Should be yyyy-MM-dd");
	    }

	    if (employee.getDateOfJoining() != null && !isValidDate(employee.getDateOfJoining().toString())) {
	        throw new InvalidEmployeeException("Invalid date of joining format. Should be yyyy-MM-dd");
	    }
	}

	private boolean isValidDate(String date) {
	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        formatter.parse(date);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}

	public void updateEmployeeFromDto(Employee employee, EmployeeDto employeeDto, PasswordEncoder passwordEncoder) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (employeeDto.getFirstName() != null && !employeeDto.getFirstName().trim().isEmpty()) {
			employee.setFirstName(employeeDto.getFirstName());
		}
		if (employeeDto.getLastName() != null && !employeeDto.getLastName().trim().isEmpty()) {
			employee.setLastName(employeeDto.getLastName());
		}
		if (employeeDto.getPersonalEmail() != null && !employeeDto.getPersonalEmail().trim().isEmpty()) {
			employee.setPersonalEmail(employeeDto.getPersonalEmail());
		}
		if (employeeDto.getLocation() != null && !employeeDto.getLocation().trim().isEmpty()) {
			employee.setLocation(employeeDto.getLocation());
		}
		if (employeeDto.getAge() != null && !employeeDto.getAge().trim().isEmpty()) {
			employee.setAge(employeeDto.getAge());
		}
		if (employeeDto.getPassword() != null && !employeeDto.getPassword().trim().isEmpty()) {
			employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
		}
		if (employeeDto.getMiddleName() != null && !employeeDto.getMiddleName().trim().isEmpty()) {
			employee.setMiddleName(employeeDto.getMiddleName());
		}

		if (employeeDto.getEmploymentSource() != null && !employeeDto.getEmploymentSource().trim().isEmpty()) {
	        employee.setEmploymentSource(employeeDto.getEmploymentSource());
	    }
	    if (employeeDto.getMobileNumber() != null && !employeeDto.getMobileNumber().trim().isEmpty()) {
	        employee.setMobileNumber(employeeDto.getMobileNumber());
	    }
	    
	    if (employeeDto.getMaritalStatus() != null && !employeeDto.getMaritalStatus().trim().isEmpty()) {
	        employee.setMaritalStatus(employeeDto.getMaritalStatus());
	    }
	    if (employeeDto.getPlaceOfBirth() != null && !employeeDto.getPlaceOfBirth().trim().isEmpty()) {
	        employee.setPlaceOfBirth(employeeDto.getPlaceOfBirth());
	    }
	    if (employeeDto.getUniqueIdentificationDocument() != null && !employeeDto.getUniqueIdentificationDocument().trim().isEmpty()) {
	        employee.setUniqueIdentificationDocument(employeeDto.getUniqueIdentificationDocument());
	    }
	    if (employeeDto.getUniqueIdentificationCode() != null && !employeeDto.getUniqueIdentificationCode().trim().isEmpty()) {
	        employee.setUniqueIdentificationCode(employeeDto.getUniqueIdentificationCode());
	    }
	    if (employeeDto.getBloodGroup() != null && !employeeDto.getBloodGroup().trim().isEmpty()) {
	        employee.setBloodGroup(employeeDto.getBloodGroup());
	    }
	    if (employeeDto.getTypeOfEmployment() != null && !employeeDto.getTypeOfEmployment().trim().isEmpty()) {
	        employee.setTypeOfEmployment(employeeDto.getTypeOfEmployment());
	    }
	    if (employeeDto.getOfficialEmail() != null && !employeeDto.getOfficialEmail().trim().isEmpty()) {
	        employee.setOfficialEmail(employeeDto.getOfficialEmail());
	    }
	    if (employeeDto.getNationality() != null && !employeeDto.getNationality().trim().isEmpty()) {
	        employee.setNationality(employeeDto.getNationality());
	    }
	    if (employeeDto.getOfficePhone() != null && !employeeDto.getOfficePhone().trim().isEmpty()) {
	        employee.setOfficePhone(employeeDto.getOfficePhone());
	    }
	    if (employeeDto.getHomePhone() != null && !employeeDto.getHomePhone().trim().isEmpty()) {
	        employee.setHomePhone(employeeDto.getHomePhone());
	    }
	    if (employeeDto.getCompany() != null && !employeeDto.getCompany().trim().isEmpty()) {
	        employee.setCompany(employeeDto.getCompany());
	    }
	    if (employeeDto.getDepartment() != null && !employeeDto.getDepartment().trim().isEmpty()) {
	        employee.setDepartment(employeeDto.getDepartment());
	    }
	    if (employeeDto.getJobTitle() != null && !employeeDto.getJobTitle().trim().isEmpty()) {
	        employee.setJobTitle(employeeDto.getJobTitle());
	    }
	    if (employeeDto.getJobLevel() != null && !employeeDto.getJobLevel().trim().isEmpty()) {
	        employee.setJobLevel(employeeDto.getJobLevel());
	    }
	    if (employeeDto.getSalutation() != null && !employeeDto.getSalutation().trim().isEmpty()) {
	        employee.setSalutation(employeeDto.getSalutation());
	    }
	    if (employeeDto.getFatherName() != null && !employeeDto.getFatherName().trim().isEmpty()) {
	        employee.setFatherName(employeeDto.getFatherName());
	    }

	    if (employeeDto.getDateOfBirth() != null) {
	        try {
	            LocalDate dateOfBirth = LocalDate.parse(employeeDto.getDateOfBirth().toString());
	            employee.setDateOfBirth(dateOfBirth);
	        } catch (DateTimeParseException e) {
	            throw new InvalidEmployeeException("Invalid date of birth format. Should be yyyy-MM-dd");
	        }
	    }
	    if (employeeDto.getDateOfJoining() != null) {
	        try {
	            LocalDate dateOfJoining = LocalDate.parse(employeeDto.getDateOfJoining().toString());
	            employee.setDateOfJoining(dateOfJoining);
	        } catch (DateTimeParseException e) {
	            throw new InvalidEmployeeException("Invalid date of joining format. Should be yyyy-MM-dd");
	        }
	    }
	    
	    
	    if (employeeDto.getEmergencyFirstName() != null && !employeeDto.getEmergencyFirstName().trim().isEmpty()) {
	        employee.setEmergencyFirstName(employeeDto.getEmergencyFirstName());
	    }
	    if (employeeDto.getEmergencyLastName() != null && !employeeDto.getEmergencyLastName().trim().isEmpty()) {
	        employee.setEmergencyLastName(employeeDto.getEmergencyLastName());
	    }
	    if (employeeDto.getEmergencyAddress() != null && !employeeDto.getEmergencyAddress().trim().isEmpty()) {
	        employee.setEmergencyAddress(employeeDto.getEmergencyAddress());
	    }
	    if (employeeDto.getEmergencyMobileNumber() != null && !employeeDto.getEmergencyMobileNumber().trim().isEmpty()) {
	        employee.setEmergencyMobileNumber(employeeDto.getEmergencyMobileNumber());
	    }
	    if (employeeDto.getEmergencyAlternateMobileNumber() != null && !employeeDto.getEmergencyAlternateMobileNumber().trim().isEmpty()) {
	        employee.setEmergencyAlternateMobileNumber(employeeDto.getEmergencyAlternateMobileNumber());
	    }
	    if (employeeDto.getEmergencyCountryName() != null && !employeeDto.getEmergencyCountryName().trim().isEmpty()) {
	        employee.setEmergencyCountryName(employeeDto.getEmergencyCountryName());
	    }
	    if (employeeDto.getEmergencyEmailId() != null && !employeeDto.getEmergencyEmailId().trim().isEmpty()) {
	        employee.setEmergencyEmailId(employeeDto.getEmergencyEmailId());
	    }
	    if (employeeDto.getEmergencyAlternateEmailId() != null && !employeeDto.getEmergencyAlternateEmailId().trim().isEmpty()) {
	        employee.setEmergencyAlternateEmailId(employeeDto.getEmergencyAlternateEmailId());
	    }
	    if (employeeDto.getAddressType() != null && !employeeDto.getAddressType().trim().isEmpty()) {
	        employee.setAddressType(employeeDto.getAddressType());
	    }
	    if (employeeDto.getAddress() != null && !employeeDto.getAddress().trim().isEmpty()) {
	        employee.setAddress(employeeDto.getAddress());
	    }
	    if (employeeDto.getCity() != null && !employeeDto.getCity().trim().isEmpty()) {
	        employee.setCity(employeeDto.getCity());
	    }
	    if (employeeDto.getTelephone() != null && !employeeDto.getTelephone().trim().isEmpty()) {
	        employee.setTelephone(employeeDto.getTelephone());
	    }
	    if (employeeDto.getZipCode() != null && !employeeDto.getZipCode().trim().isEmpty()) {
	        employee.setZipCode(employeeDto.getZipCode());
	    }
	    if (employeeDto.getCountry() != null && !employeeDto.getCountry().trim().isEmpty()) {
	        employee.setCountry(employeeDto.getCountry());
	    }
	    if (employeeDto.getState() != null && !employeeDto.getState().trim().isEmpty()) {
	        employee.setState(employeeDto.getState());
	    }
	    if (employeeDto.getDependentName() != null && !employeeDto.getDependentName().trim().isEmpty()) {
	        employee.setDependentName(employeeDto.getDependentName());
	    }

	    if (employeeDto.getDependentAge() != null && !employeeDto.getDependentAge().trim().isEmpty()) {
	        employee.setDependentAge(employeeDto.getDependentAge());
	    }

	    if (employeeDto.getDependentMobileNo() != null && !employeeDto.getDependentMobileNo().trim().isEmpty()) {
	        employee.setDependentMobileNo(employeeDto.getDependentMobileNo());
	    }

	    if (employeeDto.getDependentEmailId() != null && !employeeDto.getDependentEmailId().trim().isEmpty()) {
	        employee.setDependentEmailId(employeeDto.getDependentEmailId());
	    }

	    if (employeeDto.getDependentRelation() != null && !employeeDto.getDependentRelation().trim().isEmpty()) {
	        employee.setDependentRelation(employeeDto.getDependentRelation());
	    }

	}

	public void validateEmployeeDto(EmployeeDto employeeDto) {
		if (employeeDto.getEmployeeCode() == null || employeeDto.getEmployeeCode().trim().isEmpty()) {
			throw new RuntimeException("Employee code is required");
		}
	}
}

//public void updateEmployeeFromDto(Employee employee, EmployeeDto employeeDto, PasswordEncoder passwordEncoder) {
//employee.setFirstName(employeeDto.getFirstName());
//employee.setLastName(employeeDto.getLastName());
//employee.setPersonalEmail(employeeDto.getPersonalEmail());
//employee.setLocation(employeeDto.getLocation());
//employee.setAge(employeeDto.getAge());
//employee.setDateOfBirth(employeeDto.getDateOfBirth());
//employee.setDateOfJoining(employeeDto.getDateOfJoining());
//
//if (employeeDto.getPassword() != null && !employeeDto.getPassword().trim().isEmpty()) {
//	employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
//}
//
//validateEmployee(employee);
//}

//public void validateEmployee(Employee employee) {
//	if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()
//			|| employee.getLastName() == null || employee.getLastName().trim().isEmpty()
//			|| employee.getPersonalEmail() == null || employee.getPersonalEmail().trim().isEmpty()
//			|| employee.getLocation() == null || employee.getLocation().trim().isEmpty()
//			|| employee.getAge() == null || employee.getAge().trim().isEmpty() 
//          || employee.getDateOfBirth() == null
//			|| employee.getDateOfJoining() == null
//			|| employee.getMiddleName().trim().isEmpty()
//			|| employee.getEmploymentSource() == null || employee.getEmploymentSource().trim().isEmpty()
//			|| employee.getMobileNumber()== null || employee.getMobileNumber().trim().isEmpty())
//	{
//		throw new RuntimeException("All fields are required and cannot be empty");
//	}
//
//	if (employee.getDateOfBirth() != null && !isValidDate(employee.getDateOfBirth().toString())) {
//		throw new InvalidEmployeeException("Invalid date of birth format. Should be dd-MM-yyyy");
//	}
//
//	if (employee.getDateOfJoining() != null && !isValidDate(employee.getDateOfJoining().toString())) {
//		throw new InvalidEmployeeException("Invalid date of joining format. Should be dd-MM-yyyy");
//	}
//}
