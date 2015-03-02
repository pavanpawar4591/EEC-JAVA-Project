package com.eec.ait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author Avinash
 *
 */
public class DatabaseTest {

	public static void main(String[] args) {
		System.out.println("hi");

		try {

			// mysql
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/EEC", "root", "root");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from login");
			while (rs.next())
				System.out.println("  " + rs.getString(1) + "  "
						+ rs.getString(2));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
