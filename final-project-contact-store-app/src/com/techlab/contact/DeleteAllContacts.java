package com.techlab.contact;

public class DeleteAllContacts extends Delete {

	public DeleteAllContacts(java.sql.Connection con) {
		super(con);
	}
	public void deleteAllContacts() {
		String deleteQuery = "TRUNCATE TABLE contacts;";
		super.delete(deleteQuery);
	}
}
