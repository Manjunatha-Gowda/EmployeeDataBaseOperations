package com.te.hibernatedemo.assignment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="employee_details")
public class EmployeeDetails {
	@Id
	@Column(name="emp_id")
	private int id;
	@Column(name="emp_name")
	private String name;
	@Column(name="mobile_number")
	private long phno;
	@Column(name="emp_designation")
	private String designation;
	
	
	
	

}
