package com.techlab.contact;

public class UpdateAll extends Update {

	public UpdateAll(java.sql.Connection con) {
		super(con);
	}
	public void updateAll(String fname, String lname, String email, Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "update contacts set fname=\"" + fname + "\", lname=\"" + lname + "\", email=\""
					+ email + "\" where mobile=" + mobile + ";";
			super.update(updateQuery);
		}
	}
}
