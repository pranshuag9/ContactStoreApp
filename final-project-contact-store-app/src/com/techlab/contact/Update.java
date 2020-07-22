package com.techlab.contact;

public abstract class Update extends Contact {
	public Update(java.sql.Connection con) {
		super(con);
	}
	protected void update(String updateQuery) {
		java.sql.PreparedStatement stmt;
		try {
			stmt = super.con.prepareStatement(updateQuery);
			stmt.executeUpdate(updateQuery);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
