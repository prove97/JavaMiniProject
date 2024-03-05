package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Product;


public class ProductDAO {
	public ArrayList<Product> selectList(Connection conn) { 
		// select문(여러행 조회) -> resultSet 객체 -> ArrayList<Product>에 담기
		ArrayList<Product> list = new ArrayList<>(); //비어있는상태
		
		Statement stmt = null;
		ResultSet rset = null; //select문 실행시 조회된 결과값들이 최초로 담기는 공간
		
		//실행할 sql
		String sql = "SELECT * FROM PRODUCT ORDER BY PRODUCT_ID";
		
		try {
			stmt = conn.createStatement(); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.
			rset = stmt.executeQuery(sql);
			
		while(rset.next()) {
				Product p = new Product();
				p.setpName(rset.getString("product_name"));
				p.setCategory(rset.getString("category"));
				p.setPrice(rset.getInt("price"));
				p.setAmount(rset.getInt("amount"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	public int buyProduct(Connection conn, String pName) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE PRODUCT SET AMOUNT = (AMOUNT-1) WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, pName);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int rollbackProductAmount(Connection conn, String pName) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE PRODUCT SET AMOUNT = (AMOUNT+1) WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, pName);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public Product selectProduct(Connection conn, String pName) { 
		// select문(여러행 조회) -> resultSet 객체 -> ArrayList<Product>에 담기
		Product p = new Product();

		Statement stmt = null;
		ResultSet rset = null; 
		
		//실행할 sql
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME = '" + pName + "'";
		
		try {
			stmt = conn.createStatement(); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.
			rset = stmt.executeQuery(sql);
			
			//6) ResultSet으로부터 데이터를 하나씩 꺼내서 담아준다.
			if(rset.next()) {
				p.setpName(rset.getString("product_name"));
				p.setCategory(rset.getString("category"));
				p.setPrice(rset.getInt("price"));
				p.setAmount(rset.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return p;
	}
	
	
	
	public int addProduct(Connection conn, Product p) {
		
		int result = 0; // 처리된 결과(처리된 행수)를 받아줄 변수
		PreparedStatement pstmt = null; // 완성된 sql문 전달해서 곧바로 실행 후 결과를 받는 객체

		String sql = "INSERT INTO PRODUCT VALUES(SEQ_USERNO.NEXTVAL, ?, '음료', ?, ?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getAmount());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	
	public int deleteProduct(Connection conn, String pName) {
		//delete문 => 처리된 행 수 
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql문
			pstmt.setString(1, pName);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int productRefill(Connection conn, String pName, int amount) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET AMOUNT = (AMOUNT + ?) WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql문
			pstmt.setInt(1, amount);
			pstmt.setString(2, pName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int productRefill(Connection conn, int amount) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET AMOUNT = (AMOUNT + ?)";
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql문
			pstmt.setInt(1, amount);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	

	public int priceUpdate(Connection conn, String pName, int price) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET PRICE = ? WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql문
			pstmt.setInt(1, price);
			pstmt.setString(2, pName);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}


