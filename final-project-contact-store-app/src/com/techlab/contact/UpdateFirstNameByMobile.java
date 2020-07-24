package com.techlab.contact;

public class UpdateFirstNameByMobile extends Update{

	public UpdateFirstNameByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateFirstNameByMobile(String fname, Long mobile) {
		if (!checkMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET fname=? WHERE mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(updateQuery);
				stmt.setString(1, fname);
				stmt.setLong(2, mobile);
				super.update(stmt);
			}catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}
