package com.demo.hrms.dto;



import com.demo.hrms.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
	private String employeeCode;
	private String password;
	private String firstName;
	private String lastName;
	private String personalEmail;
	private String location;
	private String age;
	private Long adminId;
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
	private String jobLevel;
	private String salutation;
	private String fatherName;
	
	private String emergencyFirstName;
    private String emergencyLastName;
    private String emergencyAddress;
    private String emergencyMobileNumber;
    private String emergencyAlternateMobileNumber;
    private String emergencyCountryName;
    private String emergencyEmailId;
    private String emergencyAlternateEmailId;

    private String addressType;
    private String address;
    private String city;
    private String telephone;
    private String zipCode;
    private String country;
    private String state;

    
    private String standard;	
	private String course;
	private String boardUniversity;	
	private String collegeInstitution;
	private String subjects;	
	private Integer passingYear;
	private Double percentage;
	private String skill;
	private String experience;
	private String level;
	
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dateOfBirth;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dateOfJoining;
	
	
	
	public EmployeeDto(Employee employee) {
	    this.employeeCode = employee.getEmployeeCode();
	    this.password = employee.getPassword();
	    this.firstName = employee.getFirstName();
	    this.lastName = employee.getLastName();
	    this.personalEmail = employee.getPersonalEmail();
	    this.location = employee.getLocation();
	    this.age = employee.getAge();
	    this.adminId = employee.getAdmin().getId();
	    this.middleName = employee.getMiddleName();
	    this.employmentSource = employee.getEmploymentSource();
	    this.mobileNumber = employee.getMobileNumber();
	    this.maritalStatus = employee.getMaritalStatus();
	    this.placeOfBirth = employee.getPlaceOfBirth();
	    this.uniqueIdentificationDocument = employee.getUniqueIdentificationDocument();
	    this.uniqueIdentificationCode = employee.getUniqueIdentificationCode();
	    this.bloodGroup = employee.getBloodGroup();
	    this.typeOfEmployment = employee.getTypeOfEmployment();
	    this.officialEmail = employee.getOfficialEmail();
	    this.nationality = employee.getNationality();
	    this.officePhone = employee.getOfficePhone();
	    this.homePhone = employee.getHomePhone();
	    this.company = employee.getCompany();
	    this.department = employee.getDepartment();
	    this.jobLevel = employee.getJobLevel();
	    this.salutation = employee.getSalutation();
	    this.fatherName = employee.getFatherName();
	    this.dateOfBirth = employee.getDateOfBirth().toString(); 
	    this.dateOfJoining = employee.getDateOfJoining().toString();
	    this.emergencyFirstName = employee.getEmergencyFirstName();
        this.emergencyLastName = employee.getEmergencyLastName();
        this.emergencyAddress = employee.getEmergencyAddress();
        this.emergencyMobileNumber = employee.getEmergencyMobileNumber();
        this.emergencyAlternateMobileNumber = employee.getEmergencyAlternateMobileNumber();
        this.emergencyCountryName = employee.getEmergencyCountryName();
        this.emergencyEmailId = employee.getEmergencyEmailId();
        this.emergencyAlternateEmailId = employee.getEmergencyAlternateEmailId();
        this.addressType = employee.getAddressType();
        this.address = employee.getAddress();
        this.city = employee.getCity();
        this.telephone = employee.getTelephone();
        this.zipCode = employee.getZipCode();
        this.country = employee.getCountry();
        this.state = employee.getState();

        this.dateOfBirth = employee.getDateOfBirth().toString();
        this.dateOfJoining = employee.getDateOfJoining().toString();
	}

}

