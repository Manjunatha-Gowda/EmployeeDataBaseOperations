package com.te.hibernatedemo.assignment;

public class EmployeeDeatilsNotFoundException extends RuntimeException {
	String msg;

	public EmployeeDeatilsNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String getMessage() {
	
		return this.msg;
	}
	
	

}
