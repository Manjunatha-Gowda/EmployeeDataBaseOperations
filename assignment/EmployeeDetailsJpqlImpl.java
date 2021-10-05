package com.te.hibernatedemo.assignment;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeeDetailsJpqlImpl {
	static Query query = null;
	static EntityManagerFactory factory = null;
	static EntityManager manager = null;
	static EntityTransaction transaction = null;
	static EmployeeDetails employee = new EmployeeDetails();

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory("emp");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Do You want to Perform Operation on Employee Database \n Press\nYes \nOr\nNo ");
			String input = scanner.next();
			if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {

				System.out.println(
						"Please Select the Option To Display\n1.Display Data All Table Data \n2.Display  Particular Employee Data By ID\n3.Update Data Of Particular Employee\n4.To Delete Particular Employee Data From The DataBase\n5.Exit (Please Exit If You Dont Want Perform Any Operations)");
				System.out.println("----------------------------------------------------------------------");
				String choice = scanner.next();
				switch (choice) {

				case "1":
					query = manager.createQuery("from EmployeeDetails");
					List<EmployeeDetails> list = query.getResultList();
					System.out.println("\n Dispaying All The Employee Details");
					for (EmployeeDetails out : list) {
						System.out.println(out);
					}

					System.out.println("-------------------------------------------\n");
					break;
				case "2":
					System.out.println("Enter the Id To Display The Employee Details");
					int id = scanner.nextInt();
					employee = manager.find(EmployeeDetails.class, id);
					if (manager.contains(employee)) {
						query = manager.createQuery("from EmployeeDetails where id=:i");

						query.setParameter("i", id);
						employee = (EmployeeDetails) query.getSingleResult();
						System.out.println("-----------Displaying Data By Id -------------");
						System.out.println(employee);
					} else {
						try {
							throw new EmployeeDeatilsNotFoundException("Given Employee Id Not Present In The Database");
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}

					System.out.println("-------------------------------------------\n");
					break;
				case "3":
					String newName = null;
					long newPhno = 0l;
					String newDesig = null;
					String choiceOne = null;
					System.out.println("Enter the Id To Update The Employee Details");
					id = scanner.nextInt();
					employee = manager.find(EmployeeDetails.class, id);
					if (manager.contains(manager.find(EmployeeDetails.class, id))) {

						System.out.println("Do You Want to update Employee Name ( yes or no)");
						choiceOne = scanner.next();
						if (choiceOne.equalsIgnoreCase("yes") || choiceOne.equalsIgnoreCase("y")) {
							System.out.println("Enter the New Name To Update For The Employee Id: " + id);
							newName = scanner.next();
						} else {
							newName = employee.getName();
						}
						System.out.println("Do You Want to Update Employee MobileNumber ( yes or no)");
						choiceOne = scanner.next();
						if (choiceOne.equalsIgnoreCase("yes") || choiceOne.equalsIgnoreCase("y")) {
							System.out.println("Enter the New Mobile Number To Update For The Employee Id: " + id);
							newPhno = scanner.nextLong();
						} else {
							newPhno = employee.getPhno();
						}
						System.out.println("Do You Want to Update Employee Designation ( yes or no)");
						choiceOne = scanner.next();
						if (choiceOne.equalsIgnoreCase("yes") || choiceOne.equalsIgnoreCase("y")) {
							System.out.println("Enter the New Designation To Update For The Employee Id: " + id);
							newDesig = scanner.next();
						} else {
							newDesig = employee.getDesignation();
						}

						if (newName != null && newPhno != 0 && newDesig != null) {
							transaction.begin();
							query = manager.createQuery(
									"update EmployeeDetails set name=:name,designation=:desig,phno=:phno where id=:id");
							query.setParameter("name", newName);
							query.setParameter("desig", newDesig);
							query.setParameter("id", id);
							query.setParameter("phno", newPhno);

							query.executeUpdate();
							manager.persist(employee);
							transaction.commit();
							System.out.println(" Data Updated Sucessfully");
						}

					} else {
						try {
							throw new EmployeeDeatilsNotFoundException("Given Employee Id Not Present In The Database");
						} catch (Exception e) {
							System.err.println(e.getMessage());

						}
					}

					System.out.println("-------------------------------------------\n");
					break;
				case "4":
					System.out.println("Enter the Id To Display The Employee Details");
					id = scanner.nextInt();
					employee = manager.find(EmployeeDetails.class, id);
					if (manager.contains(employee)) {
						transaction.begin();
						query = manager.createQuery("delete from EmployeeDetails where id=:i");

						query.setParameter("i", id);
						query.executeUpdate();
						transaction.commit();

						System.out.println("The Id " + id + " Employee Deatils Deleted Sucessfuly");

					} else {
						try {
							throw new EmployeeDeatilsNotFoundException("Given Employee Id Not Present In The Database");
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}

					System.out.println("-------------------------------------------\n");

					break;

				case "5":
					System.exit(0);
					break;
				default:

					try {
						throw new EmployeeDeatilsNotFoundException(
								"Please check the input Value...!! and please select the Only  Given Options\n\n");
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			} else {
				if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
					System.exit(0);
				}
				try {
					throw new EmployeeDeatilsNotFoundException("Please Check the Input..!!");
				} catch (

				Exception e) {
					System.err.println(e.getMessage());
				}

			}

		}
	}

}
