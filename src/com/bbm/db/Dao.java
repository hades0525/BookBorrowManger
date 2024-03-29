package com.bbm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao
{
	private static String url = "jdbc:mysql://localhost:3306/db_bookborrow";
	private static String user = "root";
	private static String password = "root";

	private Dao()
	{
	}

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet rs, Statement st, Connection conn)
	{
		try
		{
			if (rs != null)
				rs.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (st != null)
					st.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				if (conn != null)
					try
					{
						conn.close();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
			}
		}
	}
}
