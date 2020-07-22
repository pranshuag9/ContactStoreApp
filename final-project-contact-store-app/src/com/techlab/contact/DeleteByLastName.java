package com.techlab.contact;

public class DeleteByLastName extends Delete {

	public DeleteByLastName(java.sql.Connection con) {
		super(con);
	}
	public void deleteByLastName(String lname) {
		String deleteQuery = "DELETE FROM contacts WHERE lname=\"" + lname + "\";";
		super.delete(deleteQuery);
	}
}
