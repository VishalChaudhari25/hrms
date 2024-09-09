package com.demo.hrms.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hrms.dto.AdminDto;
import com.demo.hrms.entity.Admin;
import com.demo.hrms.exception.AdminNotFoundException;
import com.demo.hrms.exception.InvalidAdminException;
import com.demo.hrms.repository.AdminRepository;

@Service
public class AdminServices {
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public ResponseEntity<Map<String, Object>> registerAdmin(AdminDto adminDto) {
		Optional<Admin> existingAdmin = adminRepository.findByEmail(adminDto.getEmail());
		if (existingAdmin.isPresent()) {
			// If the email already exists, return an error response
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("success", false);
			errorResponse.put("message", "Email already exists");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		Admin admin = new Admin();
		admin.setEmail(adminDto.getEmail());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		adminRepository.save(admin);

		// Return a success response
		Map<String, Object> successResponse = new HashMap<>();
		successResponse.put("success", true);
		successResponse.put("message", "Admin registered successfully");

		return new ResponseEntity<>(successResponse, HttpStatus.CREATED);

	}

	public ResponseEntity<Admin> loginAdmin(AdminDto adminDto) {
		Optional<Admin> admin = adminRepository.findByEmail(adminDto.getEmail());
		if (admin.isPresent()) {
			if (passwordEncoder.matches(adminDto.getPassword(), admin.get().getPassword())) {
				return new ResponseEntity<>(admin.get(), HttpStatus.OK);
			} else {
				throw new InvalidAdminException("Invalid email or password");
			}
		} else {
			throw new AdminNotFoundException("Admin not found");
		}
	}

}
//
//public ResponseEntity<Admin> loginAdmin(AdminDto adminDto) {
//    Optional<Admin> admin = adminRepository.findByEmail(adminDto.getEmail());
//    if (admin.isPresent()) {
//        if (passwordEncoder.matches(adminDto.getPassword(), admin.get().getPassword())) {
//            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
//        } else {
//            throw new InvalidAdminException("Invalid email or password");
//        }
//    } else {
//        throw new AdminNotFoundException("Admin not found");
//    }
//}
