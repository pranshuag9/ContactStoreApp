package com.techlab.contact;

public class DeleteByFirstName extends Delete {

	public DeleteByFirstName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByFirstName(String fname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=\"" + fname + "\";";
		super.delete(deleteQuery);
	}
}
