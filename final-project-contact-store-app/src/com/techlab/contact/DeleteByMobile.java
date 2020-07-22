package com.techlab.contact;

import java.sql.Connection;

public class DeleteByMobile extends Delete {

	public DeleteByMobile(Connection con) {
		super(con);
	}
	public void deleteByMobile(Long mobile) {
		if (!CheckMobileExists(mobile))
			System.err.println("Contact does not exists.");
		else {
			String deleteQuery = "DELETE FROM contacts WHERE mobile=" + mobile + ";";
			super.delete(deleteQuery);
		}
	}
}
