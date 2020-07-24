package com.techlab.contact;

public class UpdateLastNameByMobile extends Update {

	public UpdateLastNameByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateLastNameByMobile(String lname, Long mobile) {
		if (!checkMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET lname=? WHERE mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(updateQuery);
				stmt.setString(1, lname);
				stmt.setLong(2, mobile);
				super.update(stmt);
			}catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}
