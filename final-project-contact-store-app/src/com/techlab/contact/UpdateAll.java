package com.techlab.contact;

public class UpdateAll extends Update {

	public UpdateAll(java.sql.Connection con) {
		super(con);
	}
	public void updateAll(String fname, String lname, String email, Long mobile) {
		if (!checkMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET fname=?, lname=?, email=? WHERE mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(updateQuery);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, email);
				stmt.setLong(4, mobile);
				super.update(stmt);
			}catch(Exception e) {
				try {
					con.rollback();
				}catch(Exception ex) {
					System.err.println(ex);
				}
				System.err.println(e);
			}
		}
	}
}
