package com.techlab.contact;

public class ExportAllContactsToCSV extends ExportToCSV{

	public ExportAllContactsToCSV(java.sql.Connection con) {
		super(con);
	}
	public String exportAllContactsToCSV(String filename) {
		String getQuery = "SELECT * FROM contacts;";
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
			for(int i=1;i<=meta.getColumnCount();i++) {
				fw.write(meta.getColumnName(i)+(i!=meta.getColumnCount()?",":"\n"));
			}
			
			// Writing data to the file row by row
			while(rs.next()) {
				fw.write(rs.getString(1)+",");
				fw.write(rs.getString(2)+",");
				fw.write(rs.getLong(3)+",");
				fw.write(rs.getString(4)+"\n");
			}
			fw.close();
		}catch(Exception e) {
			System.err.println(e);
		}
		return filepath;
	}
}
