package com.techlab.contact.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.techlab.contact.Contact;

public class ContactTest {
	private static Scanner sc = new Scanner(System.in);
	private static class ContactPrinter{
		private void startGreeting() {
			System.out.println("Starting application...");
		}
		private void endGreeting() {
			System.out.println("Closing application...");
		}
		private void takeFirstName() {
			System.out.print("Enter First Name: ");
		}
		private void takeLastName() {
			System.out.print("Enter Last Name: ");
		}
		private void takeMobileNumber() {
			System.out.print("Enter Mobile Number: ");
		}
		private void takeEmailId() {
			System.out.print("Enter Email Id: ");
		}
		private void takeNewMobileNumber() {
			System.out.print("Enter New Mobile Number: ");
		}
		private void takeOldMobileNumber() {
			System.out.print("Enter Old Mobile Number: ");
		}
	}
	private static void displayMenu() {
		System.out.println("Select any option: ");
		System.out.println("1. Add NEW contact");
		System.out.println("2. Update contact all fields using mobile number");
		System.out.println("3. Update contact first name using mobile number");
		System.out.println("4. Update contact last name using mobile number");
		System.out.println("5. Update contact oldMobile by newMobile number");
		System.out.println("6. Update contact email using mobile number");
		System.out.println("7. Delete all contacts");
		System.out.println("8. Delete contact by mobile number");
		System.out.println("9. Delete contact/s by first name");
		System.out.println("10. Delete contact/s by last name");
		System.out.println("11. Delete contact/s by first and last name");
		System.out.println("12. Delete contact/s by email");
		System.out.println("13. Display contacts by name in ascending order");
		System.out.println("14. Display contacts by name in descending order");
		System.out.println("15. Display a contact");
		System.out.println("16. Display contacts");
		System.out.println("17. Exit");
	}
	private static int takeChoice() {
		while(!sc.hasNextInt()) sc.next();
		try {
			int choice = Integer.parseInt(sc.nextLine());
			return choice;
		}catch(NumberFormatException e) {
			System.err.println(e);
		}
		return 0;
	}
	private static String takeStringInput() {
		String s = sc.nextLine();
		return s;
	}
	private static Long takeLongInput() {
		try {
			Long l = Long.parseLong(sc.nextLine());
			return l;
		}catch(NumberFormatException e) {
			System.err.println(e);
		}
		return 0L;
	}
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/contactapp";
		String user = "root";
		String password = "root";
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,password);
			try {
				Contact contact = new Contact(con,stmt);
				int choice=-1;
				ContactPrinter printer = new ContactPrinter();
				boolean flag = true;
				printer.startGreeting();
				while(flag) {
					displayMenu();
					choice = takeChoice();
					try {
						String fname,lname,email;
						Long mobile,oldMobile,newMobile;
						switch(choice) {
							case 1: printer.takeFirstName();
									fname = takeStringInput();
									printer.takeLastName();
									lname = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									printer.takeEmailId();
									email = takeStringInput();
									contact.add(fname, lname, mobile, email);
									break;
							case 2:	printer.takeFirstName();
									fname = takeStringInput();
									printer.takeLastName();
									lname = takeStringInput();
									printer.takeEmailId();
									email = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.updateAll(fname, lname, email, mobile);
									break;
							case 3: printer.takeFirstName();
									fname = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.updateFirstNameByMobile(fname, mobile);	
									break;
							case 4: printer.takeLastName();
									lname = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.updateLastNameByMobile(lname, mobile);
									break;
							case 5: printer.takeOldMobileNumber();
									oldMobile = takeLongInput();
									printer.takeNewMobileNumber();
									newMobile = takeLongInput();
									contact.updateMobileByMobile(newMobile, oldMobile);
									break;
							case 6: printer.takeEmailId();
									email = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.updateEmailByMobile(email, mobile);
									break;
							case 7: contact.deleteAllContacts();
									break;
							case 8: printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.deleteByMobile(mobile);
									break;
							case 9: printer.takeFirstName();
									fname = takeStringInput();
									contact.deleteByFirstName(fname);
									break;
							case 10: printer.takeLastName();
									lname = takeStringInput();
									contact.deleteByLastName(lname);
									break;
							case 11: printer.takeFirstName();
									fname = takeStringInput();
									printer.takeLastName();
									lname = takeStringInput();
									contact.deleteByFirstLastName(fname, lname);
									break;
							case 12: printer.takeEmailId();
									email = takeStringInput();
									contact.deleteByEmailId(email);
									break;
							case 13:contact.displayByNameAscending();
									break;
							case 14:contact.displayByNameDescending();
									break;
							case 15:printer.takeMobileNumber();
									mobile = takeLongInput();
									contact.displayByMobile(mobile);
									break;
							case 16:contact.displayByInsertionOrder();
									break;
							default:printer.endGreeting();
									flag=false;
									break;
						}
					}catch(Exception e) {
						System.out.println(e);
					}finally {
						System.out.println();
					}
				}
			}catch(Exception e) {
				System.err.println(e);
			}
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
