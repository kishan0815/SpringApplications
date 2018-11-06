package com.tadigital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImp implements VendorDao{
	Connection con = null;
	Statement stmt = null;
	
	@Override
	public boolean loginProcess(Vendor v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "SELECT * FROM user_info WHERE username = '" + v.getUname() + "' AND password = '" + v.getPassword() + "'";
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean registerVendor(Vendor v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "INSERT INTO user_info(name,username,password) VALUES('"+v.getName()+"','"+v.getUname()+"','"+v.getPassword()+"');";
			stmt = con.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			if(rows != 0) {
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Vendor> getVendors() {
		List<Vendor> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "SELECT * FROM user_info";
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Vendor v = new Vendor();
				v.setId(rs.getInt(1));
				v.setName(rs.getString(2));
				v.setUname(rs.getString(3));
				v.setPassword(rs.getString(4));
				list.add(v);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Vendor> deleteVendor(int vid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "DELETE from user_info WHERE id = "+ vid;
			stmt = con.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			if(rows != 0) {
				List<Vendor> list = new ArrayList<>();
				sql = "SELECT * FROM user_info";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Vendor v = new Vendor();
					v.setId(rs.getInt(1));
					v.setName(rs.getString(2));
					v.setUname(rs.getString(3));
					v.setPassword(rs.getString(4));
					list.add(v);
				}
				return list;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Vendor editVendor(int vid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "SELECT * FROM user_info WHERE id = "+vid;
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				Vendor v = new Vendor();
				v.setId(rs.getInt(1));
				v.setName(rs.getString(2));
				v.setUname(rs.getString(3));
				v.setPassword(rs.getString(4));
				return v;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean updateVendor(Vendor v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "");
			String sql = "UPDATE user_info SET name= '"+v.getName()+"', "+ "username = '" + v.getUname() + "' WHERE id= "+v.getId();
			System.out.println(sql);
			stmt = con.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			if(rows != 0) {
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return false;
	}
}
