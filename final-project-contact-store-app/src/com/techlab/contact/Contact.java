package com.techlab.contact;

public abstract class Contact {
	protected java.sql.Connection con;
	protected Contact(java.sql.Connection con) {
		this.con = con;
	}
	protected boolean checkMobileExists(Long mobile) {
		String query = "SELECT count(*) FROM contacts WHERE contacts.mobile=?;";
		java.sql.ResultSet rs = null;
		int count = 0;
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = this.con.prepareStatement(query);
			stmt.setLong(1, mobile);
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				if (count == 0)
					return false;
			} catch (Exception e) {}
		}
		return true;
	}
}
