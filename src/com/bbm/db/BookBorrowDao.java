package com.bbm.db;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.bbm.model.BorrowBook;


public class BookBorrowDao
{

	public static void main(String[] args)
	{
		//borrowBook("20156545","6666666666",new Date());
		returnBook("20156545","6666666666",new Date());
		List<BorrowBook> list = selectBookBorrowReaderId("20156545");
		
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getReaderid()+"\t"+list.get(i).getISBN()+'\t'
        		   +list.get(i).getBorrowdate()+'\t'+list.get(i).getReturndate()+'\t'
        		   +list.get(i).getFine());
			          
        }
	}

	public static List<BorrowBook> selectBookBorrowReaderId(String readerid)
	{
		List<BorrowBook> list = new ArrayList<BorrowBook>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select * from borrowbook where readerid=?";
					   
			ps = conn.prepareStatement(sql);
			ps.setString(1, readerid);
			rs = ps.executeQuery();
			while (rs.next())
			{
				BorrowBook borrowbook = new BorrowBook();
				borrowbook.setReaderid(rs.getString("readerid"));
				borrowbook.setISBN(rs.getString("ISBN"));
				borrowbook.setBorrowdate(rs.getDate("borrowdate"));
				borrowbook.setReturndate(rs.getDate("returndate"));
				borrowbook.setFine(rs.getDouble("fine"));
				list.add(borrowbook);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage(), e);
		} finally
		{
			Dao.free(rs, ps, conn);
		}
		return list;
	}
	
	public static int borrowBook(String readerid,String ISBN,Date borrowdate)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="insert into borrowbook(readerid,ISBN,borrowdate) values (?,?,?) " ;
					
			ps = conn.prepareStatement(sql);
			ps.setString(1, readerid);
			ps.setString(2, ISBN);
			ps.setDate(3,new java.sql.Date(borrowdate.getTime()));
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
	
	public static int returnBook(String readerid,String ISBN,Date returndate)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="update borrowbook set returndate=? where readerid=? and ISBN=? " ;
					
			ps = conn.prepareStatement(sql);
			ps.setDate(1,new java.sql.Date(returndate.getTime()));
			ps.setString(2,readerid );
			ps.setString(3, ISBN);
			
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
