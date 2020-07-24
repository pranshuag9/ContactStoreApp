package com.techlab.contact;

public abstract class Update extends Contact {
	public Update(java.sql.Connection con) {
		super(con);
	}
	protected void update(java.sql.PreparedStatement stmt) {
		try {
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
