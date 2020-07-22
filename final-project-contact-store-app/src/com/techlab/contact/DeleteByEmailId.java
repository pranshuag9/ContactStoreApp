package com.techlab.contact;

public class DeleteByEmailId extends Delete{

	public DeleteByEmailId(java.sql.Connection con) {
		super(con);
	}
	public void deleteByEmailId(String email) {
		String deleteQuery = "DELETE FROM contacts WHERE email=\"" + email + "\";";
		super.delete(deleteQuery);
	}
}
