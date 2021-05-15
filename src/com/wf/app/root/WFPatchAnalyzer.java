package com.wf.app.root;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.wf.app.root.child.RefferedObjectAnalyzer;
import com.wf.app.root.view.ViewAnalyzer;

public class WFPatchAnalyzer {
	public static Properties PROPERTIES;
	public static Connection conn1;
	public static Connection conn2;

	public static void main(String args[]) {

		System.out.println("WFPatchAnalyzer Entering");
		System.out.println("Properties file path : " + args[0]);
		// System.out.println("Properties file name : "+args[1]);

		try {
			PROPERTIES = readProperties(args[0]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(PROPERTIES.getProperty("SCHEMA1_CONN"));
		System.out.println(PROPERTIES.getProperty("SCHEMA1_USER"));
		System.out.println(PROPERTIES.getProperty("SCHEMA1_PWD"));
		System.out.println(PROPERTIES.getProperty("SCHEMA2_CONN"));
		System.out.println(PROPERTIES.getProperty("SCHEMA2_USER"));
		System.out.println(PROPERTIES.getProperty("SCHEMA2_PWD"));
		try {
			conn1 = DriverManager.getConnection(PROPERTIES.getProperty("SCHEMA1_CONN"), PROPERTIES.getProperty("SCHEMA1_USER"),
					PROPERTIES.getProperty("SCHEMA1_PWD"));
			System.out.println("Conn1 Success");
			conn2 = DriverManager.getConnection(PROPERTIES.getProperty("SCHEMA2_CONN"),
					PROPERTIES.getProperty("SCHEMA2_USER"), PROPERTIES.getProperty("SCHEMA2_PWD"));
			System.out.println("Conn2 Success");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RefferedObjectAnalyzer refObjAl = new RefferedObjectAnalyzer(PROPERTIES);
		refObjAl.startAnlysis(conn1, conn2);
		ViewAnalyzer viewAl = new ViewAnalyzer(PROPERTIES);
		viewAl.startAnalysis(conn1, conn2);
		
		try {
			if (!conn1.isClosed()){
				conn1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!conn2.isClosed()){
				conn2.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Properties readProperties(String fileName) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		Properties prop = new Properties();
		String fl1 = fileName.replace("\\", "/");
		System.out.println(fileName);
		System.out.println(fl1);
		try {
			fis = new FileInputStream(fl1);
			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

}
