package com.techlab.contact;

public class DeleteByFirstLastName extends Delete {

	public DeleteByFirstLastName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByFirstLastName(String fname, String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=? AND lname=?;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			super.delete(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
