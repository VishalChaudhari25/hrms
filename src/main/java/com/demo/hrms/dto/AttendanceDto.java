package com.demo.hrms.dto;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {

	private Long id;
	private Date date;
	private String day;
	private Time inTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time outTime;
	private Double spentHours;
	private Double overtime;
	private String status;
	private String remarks;
	@NonNull
	private EmployeeDto employee;

}
