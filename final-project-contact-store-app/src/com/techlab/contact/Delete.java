package com.techlab.contact;

public abstract class Delete extends Contact {
	public Delete(java.sql.Connection con) {
		super(con);
	}
	protected void delete(java.sql.PreparedStatement stmt) {
		try {
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
