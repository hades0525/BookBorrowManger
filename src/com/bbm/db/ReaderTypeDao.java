package com.bbm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.bbm.model.ReaderType;

public class ReaderTypeDao
{
	public static void main(String[] args)
	{
		
		//insertReaderType(3,"职工",3,30);
		//insertReaderType(4,"校外人员",3,30);
		//updateReaderType(1,"学生",4,30);
	//	deleteReaderType(4);
		List<ReaderType> list = selectReaderType();
		//List<ReaderType> list = selectReaderType("学生");
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+'\t'+list.get(i).getMaxborrownum()+'\t'
        		  + list.get(i).getLimit());
			          
        }
	}
	
	public static List<ReaderType> selectReaderType()
	{
		List<ReaderType> list = new ArrayList<ReaderType>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			st = conn.createStatement();	
			String sql = "select * from readertype ";
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				mappingReaderType(list,rs);
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

	public static List<ReaderType> selectReaderType(String type)
	{
		List<ReaderType> list = new ArrayList<ReaderType>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select * from readertype where typename like ?";
			ps = conn.prepareStatement(sql);	
			ps.setString(1, "%"+type+"%");
			rs = ps.executeQuery();

			while (rs.next())
			{
				mappingReaderType(list,rs);
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
	
	public static int insertReaderType(int id,String typename,int num,int limit)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql="insert into readertype(id,typename,maxborrownum,`limit`) " +
					"values (?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, typename);
			ps.setInt(3, num);
			ps.setInt(4, limit);
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
	
	public static int updateReaderType(int id,String typename,int num,int limit)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql=" update readertype set id=?,typename=?,maxborrownum=?,`limit`=?  where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, typename);
			ps.setInt(3, num);
			ps.setInt(4, limit);
			ps.setInt(5, id);
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
	
	public static int deleteReaderType(int id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try
		{
			conn = Dao.getConnection();
			String sql=" delete from readertype where id=?";
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
	
	private static void mappingReaderType(List<ReaderType> list,ResultSet rs) throws SQLException
	{
		ReaderType readertype = new ReaderType();
		readertype.setId(rs.getInt("id"));
		readertype.setName(rs.getString("typename"));
		readertype.setMaxborrownum(rs.getInt("maxborrownum"));
		readertype.setLimit(rs.getInt("limit"));
		list.add(readertype);
	}
}
