package com.techlab.contact.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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
import com.techlab.contact.ExportAllContactsToCSV;
import com.techlab.contact.ExportContactEmailsToCSV;
import com.techlab.contact.ExportContactMobilesToCSV;
import com.techlab.contact.ExportContactNamesToCSV;
import com.techlab.contact.ExportContactToCSV;
import com.techlab.contact.UpdateAll;
import com.techlab.contact.UpdateEmailByMobile;
import com.techlab.contact.UpdateFirstNameByMobile;
import com.techlab.contact.UpdateLastNameByMobile;
import com.techlab.contact.UpdateMobileByMobile;

public class ContactTest {
	private static Scanner sc = new Scanner(System.in);
	private static class ContactPrinter{
		private void startGreeting() { System.out.println("Starting application..."); }
		private void endGreeting() { System.out.println("Closing application..."); }
		private void takeFirstName() { System.out.print("Enter First Name: "); }
		private void takeLastName() { System.out.print("Enter Last Name: "); }
		private void takeMobileNumber() { System.out.print("Enter Mobile Number: "); }
		private void takeEmailId() { System.out.print("Enter Email Id: "); }
		private void takeNewMobileNumber() { System.out.print("Enter New Mobile Number: "); }
		private void takeOldMobileNumber() { System.out.print("Enter Old Mobile Number: "); }
		private void takeFileName() { System.out.println("Enter File Name: "); }
		private void displayMainMenu() {
			separator();
			System.out.println("	MAIN MENU	");
			System.out.println("Select any option:");
			System.out.println("1. ADD");
			System.out.println("2. UPDATE");
			System.out.println("3. DELETE");
			System.out.println("4. DISPLAY");
			System.out.println("5. EXPORT TO CSV");
			System.out.println("6. EXIT");
		}
		private void displayUpdateMenu() {
			System.out.println("	UPDATE MENU		");
			System.out.println("Select any option:");
			System.out.println("1. Update contact all fields using mobile number");
			System.out.println("2. Update contact first name using mobile number");
			System.out.println("3. Update contact last name using mobile number");
			System.out.println("4. Update contact oldMobile by newMobile number");
			System.out.println("5. Update contact email using mobile number");
			System.out.println("6. Go back to Main Menu");
		}
		private void displayDeleteMenu() {
			System.out.println("	DELETE MENU		");
			System.out.println("Select any option:");
			System.out.println("1. Delete all contacts");
			System.out.println("2. Delete contact by mobile number");
			System.out.println("3. Delete contact/s by first name");
			System.out.println("4. Delete contact/s by last name");
			System.out.println("5. Delete contact/s by first and last name");
			System.out.println("6. Delete contact/s by email");
			System.out.println("7. Go back to Main Menu");
		}
		private void displayDisplayMenu() {
			System.out.println("	DISPLAY MENU	");
			System.out.println("Select any option:");
			System.out.println("1. Display contacts by name in ascending order");
			System.out.println("2. Display contacts by name in descending order");
			System.out.println("3. Display a contact");
			System.out.println("4. Display contacts in mobile order");
			System.out.println("5. Go back to Main Menu");
		}
		private void displayExportContactsMenu() {
			System.out.println("	EXPORT TO CSV MENU		");
			System.out.println("Select any option:");
			System.out.println("1. Export All contacts to CSV file");
			System.out.println("2. Export a contact to CSV file");
			System.out.println("3. Export contact names to CSV file");
			System.out.println("4. Export mobile numbers to CSV file");
			System.out.println("5. Export emails to CSV file");
			System.out.println("6. Go back to Main Menu");
		}
		private void displaySavedFilePath(String filepath) {
			System.out.println("File saved at: "+filepath);
		}
		private void separator() {
			System.out.println("===========================================================");
		}
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
	private static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		String filepath = "./src/resources/db.properties";
		try(FileInputStream fis = new FileInputStream(filepath)){
			props.load(fis);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(props.getProperty("mysql.url"));
		dataSource.setUser(props.getProperty("mysql.user"));
		dataSource.setPassword(props.getProperty("mysql.password"));
		return dataSource;
	}
	public static void main(String[] args) {
		DataSource dataSource = getMySQLDataSource();
		Connection con = null;
		try{
			con = dataSource.getConnection();
			try {
				Contact contact = null;
				int choice1=0,choice2=0;
				ContactPrinter printer = new ContactPrinter();
				boolean flag = true;
				printer.startGreeting();
				while(flag) {
					printer.displayMainMenu();
					choice1 = takeChoice();
					try {
						String fname,lname,email,filename,filepath;
						Long mobile,oldMobile,newMobile;
						switch(choice1) {
							case 1:
								printer.takeFirstName();
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
							case 2:
								printer.displayUpdateMenu();
								choice2 = takeChoice();
								switch(choice2) {
									case 1:
										printer.takeFirstName();
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
									case 2:
										printer.takeFirstName();
										fname = takeStringInput();
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new UpdateFirstNameByMobile(con);
										((UpdateFirstNameByMobile) contact).updateFirstNameByMobile(fname, mobile);	
										break;
									case 3:
										printer.takeLastName();
										lname = takeStringInput();
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new UpdateLastNameByMobile(con);
										((UpdateLastNameByMobile) contact).updateLastNameByMobile(lname, mobile);
										break;
									case 4:
										printer.takeOldMobileNumber();
										oldMobile = takeLongInput();
										printer.takeNewMobileNumber();
										newMobile = takeLongInput();
										contact = new UpdateMobileByMobile(con);
										((UpdateMobileByMobile) contact).updateMobileByMobile(newMobile, oldMobile);
										break;
									case 5:
										printer.takeEmailId();
										email = takeStringInput();
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new UpdateEmailByMobile(con);
										((UpdateEmailByMobile) contact).updateEmailByMobile(email, mobile);
										break;
									default: continue;
								}
								printer.separator();
								break;
							case 3:
								printer.displayDeleteMenu();
								choice2 = takeChoice();
								switch(choice2) {
									case 1:
										contact = new DeleteAllContacts(con);
										((DeleteAllContacts) contact).deleteAllContacts();
										break;
									case 2:
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new DeleteByMobile(con);
										((DeleteByMobile) contact).deleteByMobile(mobile);
										break;
									case 3:
										printer.takeFirstName();
										fname = takeStringInput();
										contact = new DeleteByFirstName(con);
										((DeleteByFirstName) contact).deleteByFirstName(fname);
										break;
									case 4:
										printer.takeLastName();
										lname = takeStringInput();
										contact = new DeleteByLastName(con);
										((DeleteByLastName) contact).deleteByLastName(lname);
										break;
									case 5:
										printer.takeFirstName();
										fname = takeStringInput();
										printer.takeLastName();
										lname = takeStringInput();
										contact = new DeleteByFirstLastName(con);
										((DeleteByFirstLastName) contact).deleteByFirstLastName(fname, lname);
										break;
									case 6:
										printer.takeEmailId();
										email = takeStringInput();
										contact = new DeleteByEmailId(con);
										((DeleteByEmailId) contact).deleteByEmailId(email);
										break;
									default: continue;
								}
								printer.separator();
								break;
							case 4:
								printer.displayDisplayMenu();
								choice2 = takeChoice();
								switch(choice2) {
									case 1:
										contact = new DisplayByNameAscending(con);
										((DisplayByNameAscending) contact).displayByNameAscending();
										break;
									case 2:
										contact = new DisplayByNameDescending(con);
										((DisplayByNameDescending) contact).displayByNameDescending();
										break;
									case 3:
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new DisplayByMobile(con);
										((DisplayByMobile) contact).displayByMobile(mobile);
										break;
									case 4:
										contact = new DisplayByMobileOrder(con);
										((DisplayByMobileOrder) contact).displayByMobileOrder();
										break;
									default: continue;
								}
								printer.separator();
								break;
							case 5:
								printer.displayExportContactsMenu();
								choice2 = takeChoice();
								switch(choice2) {
									case 1:
										printer.takeFileName();
										filename = takeStringInput();
										contact = new ExportAllContactsToCSV(con);
										filepath=((ExportAllContactsToCSV) contact).exportAllContactsToCSV(filename);
										printer.displaySavedFilePath(filepath);
										break;
									case 2:
										printer.takeFileName();
										filename = takeStringInput();
										printer.takeMobileNumber();
										mobile = takeLongInput();
										contact = new ExportContactToCSV(con);
										filepath=((ExportContactToCSV) contact).exportContactToCSV(mobile, filename);
										printer.displaySavedFilePath(filepath);
										break;
									case 3:
										printer.takeFileName();
										filename = takeStringInput();
										contact = new ExportContactNamesToCSV(con);
										filepath=((ExportContactNamesToCSV) contact).exportContactNamesToCSV(filename);
										printer.displaySavedFilePath(filepath);
										break;
									case 4:
										printer.takeFileName();
										filename = takeStringInput();
										contact = new ExportContactMobilesToCSV(con);
										filepath=((ExportContactMobilesToCSV) contact).exportContactMobilesToCSV(filename);
										printer.displaySavedFilePath(filepath);
										break;
									case 5:
										printer.takeFileName();
										filename = takeStringInput();
										contact = new ExportContactEmailsToCSV(con);
										filepath=((ExportContactEmailsToCSV) contact).exportContactEmailsToCSV(filename);
										printer.displaySavedFilePath(filepath);
										break;
									default: continue;
								}
								printer.separator();
								break;
							default: 
								printer.endGreeting();
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
