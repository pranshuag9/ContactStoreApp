package com.techlab.contact;

public abstract class ExportToCSV extends Contact{

	protected ExportToCSV(java.sql.Connection con) {
		super(con);
	}
	protected static String createCSVFile(String filename) {
		String filepath="./data/csv";
		java.io.File directory = new java.io.File(filepath);
		java.io.File file = null;
		if(!directory.exists()) 
			directory.mkdirs();
		file = new java.io.File(filepath+"/"+filename+".csv");
		if(!file.exists())
			try {
				file.createNewFile();
			}catch(Exception e) {
				System.err.println(e);
			}
		return filepath+"/"+filename+".csv";
	}
}
