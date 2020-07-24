package com.techlab.contact;

public class UpdateMobileByMobile extends Update {

	public UpdateMobileByMobile(java.sql.Connection con) {
		super(con);
	}
	public void updateMobileByMobile(Long newMobile, Long oldMobile) {
		if (checkMobileExists(newMobile)) System.err.println("New contact already exists.");
		else if (!checkMobileExists(oldMobile)) System.err.println("Old contact doesn't exists.");
		else {
			String updateQuery = "UPDATE contacts SET mobile=? where mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(updateQuery);
				stmt.setLong(1, newMobile);
				stmt.setLong(2, oldMobile);
				super.update(stmt);
			}catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}
