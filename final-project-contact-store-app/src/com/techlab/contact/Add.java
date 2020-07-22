package com.techlab.contact;

import java.sql.Connection;

public class Add extends Contact{
	private java.sql.PreparedStatement stmt;
	public Add(Connection con) {
		super(con);
	}
	public void add(String fname, String lname, Long mobile, String email) {
		if (CheckMobileExists(mobile))
			System.err.println("Contact alreay exists.");
		else {
			String addQuery = "INSERT INTO contacts VALUES(\"" + fname + "\",\"" + lname + "\"," + mobile + ",\""
					+ email + "\");";
			try {
				this.stmt = super.con.prepareStatement(addQuery);
				this.stmt.executeUpdate(addQuery);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
