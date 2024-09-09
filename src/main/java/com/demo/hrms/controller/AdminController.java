package com.demo.hrms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.hrms.dto.AdminDto;
import com.demo.hrms.dto.EmployeeDto;
import com.demo.hrms.entity.Admin;
import com.demo.hrms.entity.Employee;

import com.demo.hrms.services.AdminServices;

import com.demo.hrms.services.EmployeeServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private AdminServices adminService;
	
	@Autowired
	private EmployeeServices employeeServices;
;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerAdmin(@RequestBody AdminDto adminDto) {
        return adminService.registerAdmin(adminDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody AdminDto adminDto) {
        return adminService.loginAdmin(adminDto);
    }
    
    @GetMapping("/{adminId}/employees")
    public List<Employee> getEmployeesByAdminId(@PathVariable Long adminId) {
        return employeeServices.getEmployeesByAdminId(adminId);
    }

    @PutMapping("/{adminId}/employees/{employeeCode}")
    public Employee updateEmployee(@PathVariable Long adminId, @PathVariable String employeeCode, @RequestBody EmployeeDto employeeDto) {
        return employeeServices.updateEmployee(employeeDto, employeeCode);
    }
    
    @DeleteMapping("/{adminId}/employees/{employeeCode}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long adminId, @PathVariable String employeeCode) {
        employeeServices.deleteEmployee(employeeCode);
        return ResponseEntity.ok("Employee deleted successfully");
    }
       
}
