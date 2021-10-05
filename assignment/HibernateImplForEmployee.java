package com.te.hibernatedemo.assignment;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class HibernateImplForEmployee {
	public static void main(String[] args) {
		EmployeeDetails employee = new EmployeeDetails();
		employee.setId(40);
		employee.setName("Radhe");
		employee.setPhno(9206321581l);
		employee.setDesignation("Java Developer");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();

		manager.persist(employee);
		transaction.commit();
	}

}
