package com.techlab.contact;

public class UpdateEmailByMobile extends Update {

	public UpdateEmailByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateEmailByMobile(String email, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET email=\"" + email + "\" where mobile=" + mobile + ";";
			super.update(updateQuery);
		}
	}
}
