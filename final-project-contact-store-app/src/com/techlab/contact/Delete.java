package com.techlab.contact;

public abstract class Delete extends Contact {
	public Delete(java.sql.Connection con) {
		super(con);
	}

	protected void delete(String deleteQuery) {
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(deleteQuery);
			stmt.executeUpdate(deleteQuery);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
