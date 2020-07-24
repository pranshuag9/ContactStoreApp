package com.techlab.contact;

public class DeleteByFirstName extends Delete {

	public DeleteByFirstName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByFirstName(String fname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=?;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			stmt.setString(1, fname);
			super.delete(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
