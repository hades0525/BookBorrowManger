package com.bbm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbm.model.BookType;





public class BookTypeDao
{

	public static void main(String[] args)
	{
		//insertBookType(4,"美术");
		//updateBookType(4,"艺术");
		deleteBookType(4);
		List<BookType> list = selectBookType();
		
		//List<BookType> list = selectBookType("计算机");
		
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getTypeid()+"\t"+list.get(i).getTypename());
			          
        }
	}

	
	
	public static List<BookType> selectBookType()
	{
		List<BookType> list = new ArrayList<BookType>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			st = conn.createStatement();	
			String sql = "select * from booktype ";
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				mappingBookType(list, rs);
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

	public static List<BookType> selectBookType(String name)
	{
		List<BookType> list = new ArrayList<BookType>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select * from booktype where typename like ? ";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();

			while (rs.next())
			{
				mappingBookType(list, rs);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		
		return list;	
	}

	public static int insertBookType(int id,String typename)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="insert into booktype(id,typename) values (?,?) " ;
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, typename);
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
	
	public static int updateBookType(int id,String typename)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="update booktype set id=?,typename=? where id=? " ;
					
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, typename);
			ps.setInt(3, id);
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
	
	public static int deleteBookType(int id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql=" delete from booktype where id=?";
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
	
	private static void mappingBookType(List<BookType> list, ResultSet rs)
			throws SQLException
	{
		BookType booktype = new BookType();
		booktype.setTypeid(rs.getInt("id"));
		booktype.setTypename(rs.getString("typename"));
		list.add(booktype);
	}
}
