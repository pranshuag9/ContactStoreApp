package com.techlab.contact;

public class ExportContactEmailsToCSV extends ExportToCSV{

	public ExportContactEmailsToCSV(java.sql.Connection con) {
		super(con);
	}
	public String exportContactEmailsToCSV(String filename) {
		String getQuery = "SELECT email FROM contacts;";
		java.sql.PreparedStatement stmt = null;
		java.io.FileWriter fw = null;
		String filepath = null;
		try {
			stmt = con.prepareStatement(getQuery);
			java.sql.ResultSet rs = stmt.executeQuery();
			filepath = createCSVFile(filename);
			java.sql.ResultSetMetaData meta = rs.getMetaData();
			fw = new java.io.FileWriter(filepath);
			
			// Writing column headers to the file.
			fw.write(meta.getColumnName(1)+"\n");
			
			// Writing data to the file row by row
			while(rs.next()) {
				String email = rs.getString(1);
				if(email.length()!=0 && email!="") {
					fw.write(email+"\n");
				}
			}
			fw.close();
		}catch(Exception e) {
			System.err.println(e);
		}
		return filepath;
	}
}
