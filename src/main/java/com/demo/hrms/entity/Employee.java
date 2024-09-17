package com.demo.hrms.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	private String employeeCode;

	private String password;

	private String firstName;

	private String lastName;

	@Column(unique = true, nullable = false)
	private String personalEmail;

	private String location;
	private String age;

	private LocalDate dateOfBirth;
	private LocalDate dateOfJoining;
	private String middleName;
	private String employmentSource;
	private String mobileNumber;

	private String maritalStatus;
	private String placeOfBirth;
	private String uniqueIdentificationDocument;
	private String uniqueIdentificationCode;
	private String bloodGroup;
	private String typeOfEmployment;
	private String officialEmail;
	private String nationality;
	private String officePhone;
	private String homePhone;
	private String company;
	private String department;
	private String jobTitle;
	private String jobLevel;
	private String salutation;
	private String fatherName;

	private String emergencyFirstName;
	private String emergencyLastName;
	private String emergencyAddress;
	private String emergencyMobileNumber;
	private String emergencyCountryName;
	private String emergencyAlternateMobileNumber;
	private String emergencyEmailId;
	private String emergencyAlternateEmailId;

	private String addressType;
	private String address;
	private String city;
	private String telephone;
	private String zipCode;
	private String country;
	private String state;
	
	private String dependentName;
	private String dependentAge;
	private String dependentMobileNo;
	private String dependentEmailId;
	private String dependentRelation;


	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
}