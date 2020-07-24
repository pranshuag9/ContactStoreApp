package com.techlab.contact;

public class Add extends Contact {
	public Add(java.sql.Connection con) {
		super(con);
	}
	public void add(String fname, String lname, Long mobile, String email) {
		if (checkMobileExists(mobile))
			System.err.println("Contact alreay exists.");
		else {
			String addQuery = "INSERT INTO contacts VALUES(?,?,?,?);";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = super.con.prepareStatement(addQuery);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setLong(3, mobile);
				stmt.setString(4, email);
				stmt.executeUpdate();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
