package com.bbm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbm.model.Book;



public class BookDao
{
	public static void main(String[] args)
	{
		
		List<Book> list = selectBookByISBN("0000000000");
		//List<Book> list = selectBookByName("高等");
		//List<Book> list = selectBookByAuthor("王晶");
		//List<Book> list = selectBookByType("数");
		//List<Book> list = selectBookByPublish("同济大学");
		//insertBook("333333339","数学","离散数学","何伟","北京大学","2016-04-06",2,25.6);
		//updateBook("2222222222","数学","线性代数","李洁","同济大学","2017-01-01",5,99.9);
		//List<Book> list = selectBook();
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getISBN()+'\t'+list.get(i).getTypename()+'\t'+list.get(i).getBookname()+'\t'
        		   +list.get(i).getAuthor()+'\t'+list.get(i).getPublish()+'\t'
        		   +list.get(i).getPublishdate()+'\t'+list.get(i).getPrinttime()+'\t'+list.get(i).getUnitprice() 
        		   );
			          
        }
		//System.out.println(list);
	}
	public static List<Book> selectBook()
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			st = conn.createStatement();	
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id";
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				mappingBook(list, rs);
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


	public static List<Book> selectBookByISBN(String ISBN)
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id"+
			" where ISBN=?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1,ISBN);
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingBook(list, rs);
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
	
	public static List<Book> selectBookByName(String Bookname)
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id"+
			" where bookname like ?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+Bookname+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingBook(list, rs);
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
	
	public static List<Book> selectBookByAuthor(String author)
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id"+
			" where author like ?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+author+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingBook(list, rs);
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
	
	public static List<Book> selectBookByType(String Type)
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id"+
			" where typename like ?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+Type+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingBook(list, rs);
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
	
	public static List<Book> selectBookByPublish(String publish)
	{
		List<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select book.*,booktype.* from book join booktype on book.typeid=booktype.id"+
			" where publish like ?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+publish+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingBook(list, rs);
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
	
	public static int insertBook(String ISBN,String typename,String bookname,String author,String publish,String publishdate,int printtime,double price)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int typeid=0,i=0;
		try
		{
			conn = Dao.getConnection();
			String sql1 = "select * from booktype where typename = ?" ;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, typename);
			rs= ps.executeQuery();
			while (rs.next())
			{
				typeid=rs.getInt("id");
			}
			String sql="insert into book(ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice)"+
			"values (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ISBN);
			ps.setInt(2, typeid);
			ps.setString(3, bookname);
			ps.setString(4, author);
			ps.setString(5, publish);
			ps.setString(6, publishdate);
			ps.setInt(7, printtime);
			ps.setDouble(8, price);
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
	
	public static int updateBook(String ISBN,String typename,String bookname,String author,String publish,String publishdate,int printtime,double price)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int typeid=0,i=0;
		try
		{
			conn = Dao.getConnection();
			String sql1 = "select * from booktype where typename = ?" ;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, typename);
			rs= ps.executeQuery();
			while (rs.next())
			{
				typeid=rs.getInt("id");
			}
			String sql="update book set ISBN=?,typeid=?,bookname=?,author=?,publish=?,publishdate=?,printtime=?,unitprice=?"
					+" where ISBN=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ISBN);
			ps.setInt(2, typeid);
			ps.setString(3, bookname);
			ps.setString(4, author);
			ps.setString(5, publish);
			ps.setString(6, publishdate);
			ps.setInt(7, printtime);
			ps.setDouble(8, price);
			ps.setString(9, ISBN);
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
	
	private static void mappingBook(List<Book> list, ResultSet rs)
			throws SQLException
	{
		Book book = new Book();
		book.setISBN(rs.getString("ISBN"));
		book.setTypeid(rs.getInt("typeid"));
		book.setBookname(rs.getString("bookname"));
		book.setAuthor(rs.getString("author"));
		book.setPublish(rs.getString("publish"));
		book.setPublishdate(rs.getDate("publishdate"));
		book.setPrinttime(rs.getInt("printtime"));
		book.setUnitprice(rs.getDouble("unitprice"));
		book.setTypename(rs.getString("typename"));
		list.add(book);
	}
}
