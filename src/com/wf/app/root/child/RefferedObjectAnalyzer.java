package com.wf.app.root.child;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class RefferedObjectAnalyzer {

	String ps1 = "select name, type from user_dependencies where referenced_name = ?";
	String ps2 = "select name, type from user_dependencies where referenced_name = ?";
	PreparedStatement pStmt1;
	PreparedStatement pStmt2;
	ResultSet rs;
	ResultSet rs2;
	HashMap<String, String> hs1 = new HashMap();
	HashMap<String, String> hs2 = new HashMap();

	public RefferedObjectAnalyzer(Properties dB_PROPERTIES) {
		// TODO Auto-generated constructor stub
	}

	public void startAnlysis(Connection conn1, Connection conn2) {
		// TODO Auto-generated method stub

		try {
			pStmt1 = conn1.prepareStatement(ps1);
			pStmt1.setString(1, "STTM_CUST_ACCOUNT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs = pStmt1.executeQuery();
			while (rs.next()) {
				hs1.put(rs.getString(1), rs.getString(2));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PreparedStatement pStmt2 = conn1.prepareStatement(ps2);
			pStmt2.setString(1, "STTB_ACCOUNT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs2 = pStmt2.executeQuery();
			while (rs2.next()) {
				hs2.put(rs.getString(1), rs.getString(2));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
