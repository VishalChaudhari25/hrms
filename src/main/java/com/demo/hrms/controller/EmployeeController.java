package com.demo.hrms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hrms.dto.AttendanceDto;
import com.demo.hrms.dto.EmployeeDto;
import com.demo.hrms.entity.Attendance;
import com.demo.hrms.entity.Employee;
import com.demo.hrms.exception.AttendanceNotFoundException;
import com.demo.hrms.exception.InvalidAttendanceException;
import com.demo.hrms.services.AttendanceService;
import com.demo.hrms.services.EmployeeServices;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeService;

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/register/{adminId}")
	public Employee registerEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long adminId) {
		return employeeService.registerEmployee(employeeDto, adminId);
	}

	@PostMapping("/login")
	public Employee loginEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.loginEmployee(employeeDto);

	}

	@GetMapping("/{employeeCode}")
	public Employee getEmployeeByCode(@PathVariable String employeeCode) {
		return employeeService.getEmployeeByCode(employeeCode);
	}
	
	@PostMapping("/{employeeCode}/checkin")
    public ResponseEntity<String> checkin(@PathVariable String employeeCode) {
        try {
            attendanceService.markCheckIn(employeeCode);
            return ResponseEntity.ok("Check-in successful.");
        } catch (InvalidAttendanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{employeeCode}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String employeeCode) {
        try {
            attendanceService.markCheckOut(employeeCode);
            return ResponseEntity.ok("Check-out successfull ");
        } catch (InvalidAttendanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{employeeCode}/attendance")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeCode(@PathVariable String employeeCode) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployeeCode(employeeCode);
        if (attendanceList.isEmpty()) {
            throw new AttendanceNotFoundException("No attendance records found for employee code: " + employeeCode);
        }
        return ResponseEntity.ok(attendanceList);
    }
    

}
