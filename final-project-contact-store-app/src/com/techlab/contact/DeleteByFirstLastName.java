package com.techlab.contact;

public class DeleteByFirstLastName extends Delete {

	public DeleteByFirstLastName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByFirstLastName(String fname, String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE fname=\"" + fname + "\" AND lname=\"" + lname + "\";";
		super.delete(deleteQuery);
	}
}
