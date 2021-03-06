package com.techlab.contact;

public class DeleteByEmailId extends Delete{

	public DeleteByEmailId(java.sql.Connection con) {
		super(con);
	}
	public void deleteByEmailId(String email) {
		String deleteQuery = "DELETE FROM contacts WHERE email=?;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			stmt.setString(1, email);
			super.delete(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
