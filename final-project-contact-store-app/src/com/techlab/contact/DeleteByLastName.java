package com.techlab.contact;

public class DeleteByLastName extends Delete {

	public DeleteByLastName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByLastName(String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE lname=?;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			stmt.setString(1, lname);
			super.delete(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
