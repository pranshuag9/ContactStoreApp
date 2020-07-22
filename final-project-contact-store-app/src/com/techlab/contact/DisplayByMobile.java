package com.techlab.contact;

public class DisplayByMobile extends Display {
	public DisplayByMobile(java.sql.Connection con) {
		super(con);
	}

	public void displayByMobile(Long mobile) {
		String displayQuery = "SELECT * FROM contacts where mobile=?";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = super.con.prepareStatement(displayQuery);
			stmt.setLong(1, mobile);
			super.display(stmt);
		} catch (java.sql.SQLException e) {
			System.err.println(e);
		}
	}
}
