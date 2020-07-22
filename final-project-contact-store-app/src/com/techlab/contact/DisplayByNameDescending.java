package com.techlab.contact;

public class DisplayByNameDescending extends Display {
	public DisplayByNameDescending(java.sql.Connection con) {
		super(con);
	}
	public void displayByNameDescending() {
		String displayQuery = "SELECT * FROM contacts ORDER BY fname DESC, lname DESC;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(displayQuery);
			super.display(stmt);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
		}
	}
}
