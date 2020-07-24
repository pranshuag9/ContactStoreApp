package com.techlab.contact;

public class UpdateEmailByMobile extends Update {

	public UpdateEmailByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateEmailByMobile(String email, Long mobile) {
		if (!checkMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET email=? where mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(updateQuery);
				stmt.setString(1, email);
				stmt.setLong(2, mobile);
				super.update(stmt);
			}catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}
