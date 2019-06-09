package com.bbm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbm.model.BookType;
import com.bbm.model.Users;

public class UserDao
{

	public static void main(String[] args)
	{
		System.out.println(check("admin","123"));
		insertUser("thomas","123");//insert»±…Ÿid
		//updateUser(2,"1234");
		//deleteUser(3);
		List<Users> list = selectUser();
		
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getPassword());
			          
        }
	}
	
	public static Users check(String name,String password)
	{
		Users user = new Users();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select * from users  where name=? and password=? ";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next())
			{
				user.setName(name);
				user.setPassword(password);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		
		return user;
	}
	
	public static List<Users> selectUser()
	{
		List<Users> list = new ArrayList<Users>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			st = conn.createStatement();	
			String sql = "select * from Users ";
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		} finally
		{
			Dao.free(rs, st, conn);
		}
		
		return list;	
	}
	
	public static int insertUser(String name,String pwd)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="insert into users(name,password) values (?,?) " ;
					
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			i = ps.executeUpdate();
			
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(), e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		return i;
	}
	
	public static int updateUser(String name,String pwd)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="update users set password=? where name=? " ;
					
			ps = conn.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, name);
			i = ps.executeUpdate();
			
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(), e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		return i;
	}
	
	public static int deleteUser(int id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="delete from users where id=? " ;
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
			
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(), e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		return i;
	}
	
}
