package com.techlab.contact;

public abstract class Display extends Contact{
	public Display(java.sql.Connection con) {
		super(con);
	}
	protected void display(java.sql.PreparedStatement stmt) {
		try {
			java.sql.ResultSet rs = stmt.executeQuery();
			
			int maxLenFname = 0;
			int maxLenLname = 0;
			int maxLenEmail = 0;

			while (rs.next()) {
				String fname = (rs.getString(1) == null) ? "" : rs.getString(1);
				String lname = (rs.getString(2) == null) ? "" : rs.getString(2);
				String email = (rs.getString(4) == null) ? "" : rs.getString(4);

				if (maxLenFname < fname.length())
					maxLenFname = fname.length();
				if (maxLenLname < lname.length())
					maxLenLname = lname.length();
				if (maxLenEmail < email.length())
					maxLenEmail = email.length();
			}

			rs = stmt.executeQuery();

			if (maxLenFname < "fname".length()) maxLenFname = "fname".length();
			if (maxLenLname < "lname".length()) maxLenLname = "lname".length();
			if (maxLenEmail < "email".length()) maxLenEmail = "email".length();

			String headerFname = String.format("%-" + (maxLenFname + 1) + "s", "fname");
			String headerLname = String.format("%-" + (maxLenLname + 1) + "s", "lname");
			String headerMobile = String.format("%-11s", "mobile");
			String headerEmail = String.format("%-" + (maxLenEmail + 1) + "s", "email");
			System.out.println(headerFname + "|" + headerLname + "|" + headerMobile + "|" + headerEmail + "|");

			while (rs.next()) {
				String fname = (rs.getString(1) == null) ? "" : rs.getString(1);
				String lname = (rs.getString(2) == null) ? "" : rs.getString(2);
				String mobile = rs.getString(3);
				String email = (rs.getString(4) == null) ? "" : rs.getString(4);

				String formatCodeFname = "%-" + (maxLenFname + 1) + "s";
				String formatCodeLname = "%-" + (maxLenLname + 1) + "s";
				String formatCodeEmail = "%-" + (maxLenEmail + 1) + "s";

				String paddedFname = String.format(formatCodeFname, fname);
				String paddedLname = String.format(formatCodeLname, lname);
				String paddedMobile = String.format("%-11s", mobile);
				String paddedEmail = String.format(formatCodeEmail, email);

				System.out.print(paddedFname + "|");
				System.out.print(paddedLname + "|");
				System.out.print(paddedMobile + "|");
				System.out.print(paddedEmail + "|");
				System.out.println();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
