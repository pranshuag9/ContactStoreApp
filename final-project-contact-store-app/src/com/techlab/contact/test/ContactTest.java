package com.techlab.contact.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.techlab.contact.Add;
import com.techlab.contact.Contact;
import com.techlab.contact.DeleteAllContacts;
import com.techlab.contact.DeleteByEmailId;
import com.techlab.contact.DeleteByFirstLastName;
import com.techlab.contact.DeleteByFirstName;
import com.techlab.contact.DeleteByLastName;
import com.techlab.contact.DeleteByMobile;
import com.techlab.contact.DisplayByMobileOrder;
import com.techlab.contact.DisplayByMobile;
import com.techlab.contact.DisplayByNameAscending;
import com.techlab.contact.DisplayByNameDescending;
import com.techlab.contact.UpdateAll;
import com.techlab.contact.UpdateEmailByMobile;
import com.techlab.contact.UpdateFirstNameByMobile;
import com.techlab.contact.UpdateLastNameByMobile;
import com.techlab.contact.UpdateMobileByMobile;

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
		System.out.println("16. Display contacts in mobile order");
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
		try{
			con = DriverManager.getConnection(url,user,password);
			try {
				Contact contact = null;
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
									contact = new Add(con);
									((Add) contact).add(fname,lname,mobile,email);
									break;
							case 2:	printer.takeFirstName();
									fname = takeStringInput();
									printer.takeLastName();
									lname = takeStringInput();
									printer.takeEmailId();
									email = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new UpdateAll(con);
									((UpdateAll) contact).updateAll(fname, lname, email, mobile);
									break;
							case 3: printer.takeFirstName();
									fname = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new UpdateFirstNameByMobile(con);
									((UpdateFirstNameByMobile) contact).updateFirstNameByMobile(fname, mobile);	
									break;
							case 4: printer.takeLastName();
									lname = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new UpdateLastNameByMobile(con);
									((UpdateLastNameByMobile) contact).updateLastNameByMobile(lname, mobile);
									break;
							case 5: printer.takeOldMobileNumber();
									oldMobile = takeLongInput();
									printer.takeNewMobileNumber();
									newMobile = takeLongInput();
									contact = new UpdateMobileByMobile(con);
									((UpdateMobileByMobile) contact).updateMobileByMobile(newMobile, oldMobile);
									break;
							case 6: printer.takeEmailId();
									email = takeStringInput();
									printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new UpdateEmailByMobile(con);
									((UpdateEmailByMobile) contact).updateEmailByMobile(email, mobile);
									break;
							case 7: contact = new DeleteAllContacts(con);
									((DeleteAllContacts) contact).deleteAllContacts();
									break;
							case 8: printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new DeleteByMobile(con);
									((DeleteByMobile) contact).deleteByMobile(mobile);
									break;
							case 9: printer.takeFirstName();
									fname = takeStringInput();
									contact = new DeleteByFirstName(con);
									((DeleteByFirstName) contact).deleteByFirstName(fname);
									break;
							case 10: printer.takeLastName();
									lname = takeStringInput();
									contact = new DeleteByLastName(con);
									((DeleteByLastName) contact).deleteByLastName(lname);
									break;
							case 11: printer.takeFirstName();
									fname = takeStringInput();
									printer.takeLastName();
									lname = takeStringInput();
									contact = new DeleteByFirstLastName(con);
									((DeleteByFirstLastName) contact).deleteByFirstLastName(fname, lname);
									break;
							case 12: printer.takeEmailId();
									email = takeStringInput();
									contact = new DeleteByEmailId(con);
									((DeleteByEmailId) contact).deleteByEmailId(email);
									break;
							case 13:contact = new DisplayByNameAscending(con);
									((DisplayByNameAscending) contact).displayByNameAscending();
									break;
							case 14:contact = new DisplayByNameDescending(con);
									((DisplayByNameDescending) contact).displayByNameDescending();
									break;
							case 15:printer.takeMobileNumber();
									mobile = takeLongInput();
									contact = new DisplayByMobile(con);
									((DisplayByMobile) contact).displayByMobile(mobile);
									break;
							case 16:contact = new DisplayByMobileOrder(con);
									((DisplayByMobileOrder) contact).displayByMobileOrder();
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
