package com.bbm.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbm.model.Reader;



public class ReaderDao 
{
	//
	public static void main(String[] args)
	{
		
		//List<Reader> list = selectReaderByName("李");
		List<Reader> list = selectReaderById("20166666");
		//insertReader("20165858","学生","李四",25,"男","115451345","美术","2016-07-01");
		//updateReader("20165858","学生","李四",33,"男","115451345","美术","2016-07-01");
		//List<Reader> list = selectReader();
		for(int i=0;i<list.size();i++)
        {
           System.out.println(list.get(i).getTypename()+'\t'+list.get(i).getReaderid()+'\t'+list.get(i).getName()+'\t'
        		   +list.get(i).getAge()+'\t'+list.get(i).getSex()+'\t'
        		   +list.get(i).getDept()+'\t'+list.get(i).getPhone()+'\t'+list.get(i).getRegdate()+'\t'
        		   +list.get(i).getMaxborrownum()+'\t'+list.get(i).getLimit()+'\t');
			          
        }
		
		
	}
	
	public static List<Reader> selectReader()
	{
		List<Reader> list = new ArrayList<Reader>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			st = conn.createStatement();
//			String sql = "select readerid,type,name, age, sex,phone,dept,regdate,typename,maxborrownum,limit " +
//					" from reader  join readertype on reader.type=readertype.id";		
			String sql = "select reader.*,readertype.* from reader join readertype on reader.type=readertype.id";
			rs = st.executeQuery(sql);

			// 5.处理结果
			while (rs.next())
			{
				mappingReader(list, rs);
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
	
	public static List<Reader> selectReaderById(String id)
	{
		List<Reader> list = new ArrayList<Reader>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select reader.*,readertype.* from reader join readertype on reader.type = readertype.id" +
					  " where readerid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingReader(list,rs);
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
	
	public static List<Reader> selectReaderByName(String name)
	{
		List<Reader> list = new ArrayList<Reader>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select reader.*,readertype.* from reader join readertype on reader.type = readertype.id" +
					  " where name like ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingReader(list,rs);
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
	
	public static List<Reader> selectReaderByDept(String dept)
	{
		List<Reader> list = new ArrayList<Reader>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select reader.*,readertype.* from reader join readertype on reader.type = readertype.id" +
					  " where dept like ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+dept+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingReader(list,rs);
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
	
	public static List<Reader> selectReaderByType(String type)
	{
		List<Reader> list = new ArrayList<Reader>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = Dao.getConnection();
			String sql = "select reader.*,readertype.* from reader join readertype on reader.type = readertype.id" +
					  " where typename like ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+type+"%");
			rs = ps.executeQuery();
			while (rs.next())
			{
				mappingReader(list,rs);
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
	
	public static int insertReader(String id,String typename,String name,int age,String sex,String phone,String dept,String reg)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int typeid=0,i=0;
		try
		{
			conn = Dao.getConnection();
			String sql1 = "select * from readertype where typename = ?" ;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, typename);
			rs= ps.executeQuery();
			while (rs.next())
			{
				typeid=rs.getInt("id");
			}
			String sql="insert into reader(readerid,type,name,age,sex,phone,dept,regdate)"+
			"values (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, typeid);
			ps.setString(3, name);
			ps.setInt(4, age);
			ps.setString(5, sex);
			ps.setString(6, phone);
			ps.setString(7, dept);
			ps.setString(8, reg);
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
	
	public static int updateReader(String id,String typename,String name,int age,String sex,String phone,String dept,String reg)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int typeid=0,i=0;
		try
		{
			conn = Dao.getConnection();
			String sql1 = "select * from readertype where typename = ?" ;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, typename);
			rs= ps.executeQuery();
			while (rs.next())
			{
				typeid=rs.getInt("id");
			}
			String sql="update reader set readerid=?,type=?,name=?,age=?,sex=?,phone=?,dept=?,regdate=? where readerid=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, typeid);
			ps.setString(3, name);
			ps.setInt(4, age);
			ps.setString(5, sex);
			ps.setString(6, phone);
			ps.setString(7, dept);
			ps.setString(8, reg);
			ps.setString(9, id);
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
	
	private static void mappingReader(List<Reader> list, ResultSet rs)
			throws SQLException
	{
		Reader reader = new Reader();
		reader.setReaderid(rs.getInt("readerid"));
		reader.setType(rs.getInt("type"));
		reader.setName(rs.getString("name"));
		reader.setAge(rs.getInt("age"));
		reader.setSex(rs.getString("sex"));
		reader.setPhone(rs.getString("phone"));
		reader.setDept(rs.getString("dept"));
		reader.setRegdate(rs.getDate("regdate"));
		reader.setTypename(rs.getString("typename"));
		reader.setMaxborrownum(rs.getInt("maxborrownum"));
		reader.setLimit(rs.getInt("limit"));
		list.add(reader);
	}
}
