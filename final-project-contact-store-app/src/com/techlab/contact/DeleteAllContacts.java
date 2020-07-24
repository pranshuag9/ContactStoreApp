package com.techlab.contact;

public class DeleteAllContacts extends Delete {

	public DeleteAllContacts(java.sql.Connection con) {
		super(con);
	}
	public void deleteAllContacts() {
		String deleteQuery = "TRUNCATE TABLE contacts;";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			super.delete(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
