package com.demo.hrms.entity;

import java.sql.Date;
import java.sql.Time;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "attendance", uniqueConstraints = { @UniqueConstraint(columnNames = { "employee_code", "date" }) })
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@Column(name = "day")
	private String day;

	@Column(name = "in_time")
	private Time inTime;

	@Column(name = "out_time")
	private Time outTime;

	@Column(name = "spent_hours")
	private Double spentHours;

	@Column(name = "is_manual")
	private String isManual;

	@Column(name = "status")
	private String status;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "missed_punch_time")
	private Time missedPunchTime;

	@Column(name = "overtime")
	private Double overtime;

	@ManyToOne
	@JoinColumn(name = "employee_code", referencedColumnName = "employeeCode")
	private Employee employee;
}