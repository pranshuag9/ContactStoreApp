package com.techlab.contact;

public class UpdateLastNameByMobile extends Update {

	public UpdateLastNameByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateLastNameByMobile(String lname, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET lname=\"" + lname + "\" where mobile=" + mobile + ";";
			super.update(updateQuery);
		}
	}
}
