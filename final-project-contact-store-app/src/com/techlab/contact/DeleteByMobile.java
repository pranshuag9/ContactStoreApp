package com.techlab.contact;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteByMobile extends Delete {

	public DeleteByMobile(Connection con) {
		super(con);
	}
	public void deleteByMobile(Long mobile) {
		if (!checkMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String deleteQuery = "DELETE FROM contacts WHERE mobile=?;";
			java.sql.PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(deleteQuery);
				stmt.setLong(1, mobile);
				super.delete(stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
