package com.demo.hrms.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.demo.hrms.dto.AttendanceDto;
import com.demo.hrms.entity.Attendance;
import com.demo.hrms.exception.InvalidAttendanceException;

@Service
public class AttendanceUtil {
	private ModelMapper modelMapper;

	public AttendanceUtil(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Attendance mapToEntity(AttendanceDto attendanceDto) {
		return modelMapper.map(attendanceDto, Attendance.class);
	}

	public void validateAttendance(Attendance attendance, boolean isCheckIn) {
		if (attendance.getEmployee() == null) {
			throw new InvalidAttendanceException("Employee is required");
		}
		if (attendance.getDate() == null) {
			throw new InvalidAttendanceException("Date is required");
		}
		if (attendance.getInTime() == null) {
			throw new InvalidAttendanceException("In time is required");
		}
		// Only require outTime for check-out operations
		if (!isCheckIn && attendance.getOutTime() == null) {
			throw new InvalidAttendanceException("Out time is required");
		}
	}

	public void calculateSpentHours(Attendance attendance) {
		if (attendance.getInTime() != null && attendance.getOutTime() != null) {
			LocalTime inTime = attendance.getInTime().toLocalTime();
			LocalTime outTime = attendance.getOutTime().toLocalTime();
			Duration duration = Duration.between(inTime, outTime);
			long spentHours = duration.toHours();
			attendance.setSpentHours((double) spentHours);
			if (spentHours > 9) {
				attendance.setOvertime((double) (spentHours - 9));
			} else {
				attendance.setOvertime(0.0);
			}
		}
	}

	public void updateAttendanceStatus(Attendance attendance) {
		if (attendance.getSpentHours() != null) {
			// Check if it's a weekend (Saturday or Sunday)
			DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
			boolean isWeekend = (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);

			// Check the spent hours and set status accordingly
			if (attendance.getSpentHours() >= 9) {
				attendance.setStatus("P-P");
				attendance.setRemarks("Full Day");
			} else if (attendance.getSpentHours() > 0 && attendance.getSpentHours() < 9) {
				attendance.setStatus("P-A");
				attendance.setRemarks("Absent");
			} else if (attendance.getSpentHours() == 0) {
				attendance.setStatus("A-A");
				attendance.setRemarks("Absent");
			} else if (isWeekend) {
				attendance.setStatus("W-W");
				attendance.setRemarks("Week Off");
			}
		}
	}

}