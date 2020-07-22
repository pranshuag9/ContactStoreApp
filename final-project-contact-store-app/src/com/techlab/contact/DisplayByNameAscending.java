package com.techlab.contact;

public class DisplayByNameAscending extends Display{
	public DisplayByNameAscending(java.sql.Connection con) {
		super(con);
	}
	public void displayByNameAscending() {
		String displayQuery = "SELECT * FROM contacts ORDER BY fname ASC, lname ASC;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(displayQuery);
			super.display(stmt);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
		}
	}
}
