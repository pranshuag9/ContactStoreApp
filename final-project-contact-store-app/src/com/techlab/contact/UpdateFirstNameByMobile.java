package com.techlab.contact;

public class UpdateFirstNameByMobile extends Update{

	public UpdateFirstNameByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateFirstNameByMobile(String fname, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET fname=\"" + fname + "\" where mobile=" + mobile + ";";
			super.update(updateQuery);
		}
	}
}
