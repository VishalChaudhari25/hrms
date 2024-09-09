package com.demo.hrms.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.hrms.entity.Attendance;
import com.demo.hrms.entity.Employee;
import com.demo.hrms.exception.AttendanceNotFoundException;
import com.demo.hrms.exception.InvalidAttendanceException;
import com.demo.hrms.repository.AttendanceRepository;
import com.demo.hrms.repository.EmployeeRepository;
import com.demo.hrms.util.AttendanceUtil;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AttendanceUtil attendanceUtil;

	public Attendance markCheckIn(String employeeCode) {
		Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeCode);
		if (!employee.isPresent()) {
			throw new AttendanceNotFoundException("Employee not found");
		}

		// Check if the employee has already checked in today
		boolean alreadyCheckedIn = attendanceRepository.existsByEmployeeAndDateAndOutTimeIsNull(employee.get(),
				java.sql.Date.valueOf(LocalDate.now()));
		if (alreadyCheckedIn) {
			throw new InvalidAttendanceException("Already checked in. Please check out before checking in again.");
		}

		Attendance attendance = new Attendance();
		attendance.setEmployee(employee.get());
		attendance.setDate(java.sql.Date.valueOf(LocalDate.now()));
		attendance.setInTime(java.sql.Time.valueOf(LocalTime.now()));
		attendance.setDay(DayOfWeek.from(LocalDate.now()).toString()); // Set the current day
		// Automatically mark Saturdays and Sundays as week-off
		DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			attendance.setStatus("W-W");
			attendance.setRemarks("Week Off");
		}

		// Validate only check-in specific fields
		try {
			attendanceUtil.validateAttendance(attendance, true);
		} catch (InvalidAttendanceException e) {
			throw new InvalidAttendanceException(e.getMessage());
		}

		return attendanceRepository.save(attendance);
	}

	public Attendance markCheckOut(String employeeCode) {
		Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeCode);
		if (!employee.isPresent()) {
			throw new AttendanceNotFoundException("Employee not found");
		}

		// Fetch the attendance record for today
		Attendance attendance = attendanceRepository
				.findByEmployeeAndDateAndOutTimeIsNull(employee.get(), java.sql.Date.valueOf(LocalDate.now()))
				.orElseThrow(() -> new InvalidAttendanceException("Attendance record not found"));

		if (attendance.getInTime() == null) {
			throw new InvalidAttendanceException("Check-in time is missing");
		}

		attendance.setOutTime(java.sql.Time.valueOf(LocalTime.now()));
		attendance.setIsManual("No");

		try {
			// Validate check-out specific fields
			attendanceUtil.validateAttendance(attendance, false);
			attendanceUtil.calculateSpentHours(attendance);
			attendanceUtil.updateAttendanceStatus(attendance);
		} catch (InvalidAttendanceException e) {
			throw new InvalidAttendanceException(e.getMessage());
		}

		return attendanceRepository.save(attendance);
	}
	

	// Automatically check-out employees after 9 hours + 2 hours grace period if
	// they forget to check out
	@Scheduled(cron = "0 0 16 * * *") // Runs at 22:00 every day
	public void autoCheckOutEmployees() {
		List<Attendance> unattendedCheckIns = attendanceRepository.findByOutTimeIsNull();
		LocalTime currentTime = LocalTime.now();

		for (Attendance attendance : unattendedCheckIns) {
			LocalTime inTime = attendance.getInTime().toLocalTime();
			if (currentTime.isAfter(LocalTime.of(16, 0))) { // Auto check-out at 22:00 if not done manually
				attendance.setOutTime(java.sql.Time.valueOf(LocalTime.of(22, 0)));
				attendance.setMissedPunchTime(java.sql.Time.valueOf(LocalTime.of(22, 0)));
				attendance.setIsManual("Yes");

				attendanceUtil.calculateSpentHours(attendance);
				attendanceUtil.updateAttendanceStatus(attendance);
				attendanceRepository.save(attendance);
			}
		}
	}

	public List<Attendance> getAttendanceByEmployeeCode(String employeeCode) {
		List<Attendance> attendanceList = attendanceRepository.findByEmployee_EmployeeCodeOrderByDateDesc(employeeCode);
		if (attendanceList.isEmpty()) {
			throw new AttendanceNotFoundException("No attendance records found for employee code: " + employeeCode);
		}
		return attendanceList;
	}

}