package com.techlab.contact;

public class DisplayByMobileOrder extends Display {
	public DisplayByMobileOrder(java.sql.Connection con) {
		super(con);
	}
	public void displayByMobileOrder() {
		String displayQuery = "SELECT * FROM contacts;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = super.con.prepareStatement(displayQuery);
			super.display(stmt);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
		}
	}
}
