package com.techlab.contact;

public class UpdateMobileByMobile extends Update {

	public UpdateMobileByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateMobileByMobile(Long newMobile, Long oldMobile) {
		if (!CheckMobileExists(oldMobile))
			System.err.println("Contact does not exists.");
		else {
			String updateQuery = "UPDATE contacts SET mobile=" + newMobile + " where mobile=" + oldMobile + ";";
			super.update(updateQuery);
		}
	}
}
