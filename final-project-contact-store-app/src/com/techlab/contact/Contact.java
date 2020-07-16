package com.techlab.contact;

import java.sql.SQLException;

public class Contact {
	private java.sql.Connection con;
	private java.sql.PreparedStatement stmt;

	public Contact(java.sql.Connection con, java.sql.PreparedStatement stmt) throws SQLException {
		this.con = con;
		this.stmt = stmt;
	}

	private boolean CheckMobileExists(Long mobile) {
		String query = "SELECT count(*) FROM contacts WHERE contacts.mobile=" + mobile + ";";
		java.sql.ResultSet rs = null;
		int count = 0;
		try {
			rs = this.stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				if (count == 0)
					return false;
			} catch (Exception e) {

			}
		}
		return true;
	}

	private void display() {
		try {
			java.sql.ResultSet rs = this.stmt.executeQuery();

			int maxLenFname = 0;
			int maxLenLname = 0;
			int maxLenEmail = 0;

			while (rs.next()) {
				String fname = (rs.getString(1) == null) ? "" : rs.getString(1);
				String lname = (rs.getString(2) == null) ? "" : rs.getString(2);
				String email = (rs.getString(4) == null) ? "" : rs.getString(4);

				if (maxLenFname < fname.length())
					maxLenFname = fname.length();
				if (maxLenLname < lname.length())
					maxLenLname = lname.length();
				if (maxLenEmail < email.length())
					maxLenEmail = email.length();
			}

			rs = this.stmt.executeQuery();

			if (maxLenFname < "fname".length())
				maxLenFname = "fname".length();
			if (maxLenLname < "lname".length())
				maxLenLname = "lname".length();
			if (maxLenEmail < "email".length())
				maxLenEmail = "email".length();

			String headerFname = String.format("%-" + (maxLenFname + 1) + "s", "fname");
			String headerLname = String.format("%-" + (maxLenLname + 1) + "s", "lname");
			String headerMobile = String.format("%-11s", "mobile");
			String headerEmail = String.format("%-" + (maxLenEmail + 1) + "s", "email");
			System.out.println(headerFname + "|" + headerLname + "|" + headerMobile + "|" + headerEmail + "|");

			while (rs.next()) {
				String fname = (rs.getString(1) == null) ? "" : rs.getString(1);
				String lname = (rs.getString(2) == null) ? "" : rs.getString(2);
				String mobile = rs.getString(3);
				String email = (rs.getString(4) == null) ? "" : rs.getString(4);

				String formatCodeFname = "%-" + (maxLenFname + 1) + "s";
				String formatCodeLname = "%-" + (maxLenLname + 1) + "s";
				String formatCodeEmail = "%-" + (maxLenEmail + 1) + "s";

				String paddedFname = String.format(formatCodeFname, fname);
				String paddedLname = String.format(formatCodeLname, lname);
				String paddedMobile = String.format("%-11s", mobile);
				String paddedEmail = String.format(formatCodeEmail, email);

				System.out.print(paddedFname + "|");
				System.out.print(paddedLname + "|");
				System.out.print(paddedMobile + "|");
				System.out.print(paddedEmail + "|");
				System.out.println();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void displayByInsertionOrder() {
		String displayQuery = "SELECT * FROM contacts;";
		try {
			this.stmt = con.prepareStatement(displayQuery);
		} catch (SQLException e) {
			System.err.println(e);
		}
		display();
	}

	public void displayByNameAscending() {
		String displayQuery = "SELECT * FROM contacts ORDER BY fname ASC, lname ASC;";
		try {
			this.stmt = con.prepareStatement(displayQuery);
		} catch (SQLException e) {
			System.err.println(e);
		}
		display();
	}

	public void displayByNameDescending() {
		String displayQuery = "SELECT * FROM contacts ORDER BY fname DESC, lname DESC;";
		try {
			this.stmt = con.prepareStatement(displayQuery);
		} catch (SQLException e) {
			System.err.println(e);
		}

		display();
	}

	public void displayByMobile(Long mobile) {
		String displayQuery = "SELECT * FROM contacts where mobile=?";
		try {
			this.stmt = con.prepareStatement(displayQuery);
			this.stmt.setLong(1, mobile);
		} catch (SQLException e) {
			System.err.println(e);
		}
		display();
	}

	public void add(String fname, String lname, Long mobile, String email) {
		if (CheckMobileExists(mobile))
			System.err.println("Contact alreay exists.");
		else {
			String addQuery = "INSERT INTO contacts VALUES(\"" + fname + "\",\"" + lname + "\"," + mobile + ",\""
					+ email + "\");";
			try {
				this.stmt.executeUpdate(addQuery);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	private void delete(String deleteQuery) {
		try {
			this.stmt.executeUpdate(deleteQuery);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void deleteByMobile(Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String deleteQuery = "DELETE FROM contacts WHERE mobile=" + mobile + ";";
			delete(deleteQuery);
		}
	}

	public void deleteByFirstName(String fname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=\"" + fname + "\";";
		delete(deleteQuery);
	}

	public void deleteByLastName(String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE lname=\"" + lname + "\";";
		delete(deleteQuery);
	}

	public void deleteByFirstLastName(String fname, String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=\"" + fname + "\" AND lname=\"" + lname + "\";";
		delete(deleteQuery);
	}

	public void deleteByEmailId(String email) {
		String deleteQuery = "DELETE FROM contacts WHERE email=\"" + email + "\";";
		delete(deleteQuery);
	}

	public void deleteAllContacts() {
		String deleteQuery = "TRUNCATE TABLE contacts;";
		delete(deleteQuery);
	}

	private void update(String updateQuery) {
		try {
			this.stmt.executeUpdate(updateQuery);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void updateFirstNameByMobile(String fname, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET fname=\"" + fname + "\" where mobile=" + mobile + ";";
			update(updateQuery);
		}
	}

	public void updateLastNameByMobile(String lname, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET lname=\"" + lname + "\" where mobile=" + mobile + ";";
			update(updateQuery);
		}
	}

	public void updateMobileByMobile(Long newMobile, Long oldMobile) {
		if (!CheckMobileExists(oldMobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET mobile=" + newMobile + " where mobile=" + oldMobile + ";";
			update(updateQuery);
		}
	}

	public void updateEmailByMobile(String email, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET email=\"" + email + "\" where mobile=" + mobile + ";";
			update(updateQuery);
		}
	}

	public void updateAll(String fname, String lname, String email, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "update contacts set fname=\"" + fname + "\", lname=\"" + lname + "\", email=\""
					+ email + "\" where mobile=" + mobile + ";";
			update(updateQuery);
		}
	}
}
