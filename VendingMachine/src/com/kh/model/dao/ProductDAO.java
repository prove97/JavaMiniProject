package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Product;


public class ProductDAO {

	public ArrayList<Product> selectList() { 
		// select문(여러행 조회) -> resultSet 객체 -> ArrayList<Product>에 담기
		
		//필요한 변수를 세팅
		ArrayList<Product> list = new ArrayList<>(); //비어있는상태
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; //select문 실행시 조회된 결과값들이 최초로 담기는 공간
		
		//실행할 sql
		String sql = "SELECT * FROM PRODUCT ORDER BY PRODUCT_ID";
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		
			//3) Statemente 객체 생성
			stmt = conn.createStatement(); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.

			//4,5) sql전송 및 결과받기
			rset = stmt.executeQuery(sql);
			
			//6) ResultSet으로부터 데이터를 하나씩 꺼내서 담아준다.
			while(rset.next()) {
				Product p = new Product();
				p.setpName(rset.getString("product_name"));
				p.setPrice(rset.getInt("price"));
				p.setAmount(rset.getInt("amount"));
				//현재 참조하고있는 행에 대한 모든 컬럼에 데이터들을 한 Product객체에 담기
				
				list.add(p);
			}
			//반복문이 끝난 시점
			//조회된 데이터가 없었다면 리스트는 비어있을 것이다.
			//조회된 데이터가 있다면 전부 list에 담겨있을 것이다.
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
	
	public Product selectProduct(String pName) { 
		// select문(여러행 조회) -> resultSet 객체 -> ArrayList<Product>에 담기
		
		//필요한 변수를 세팅
		Product p = new Product(); //비어있는상태
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; //select문 실행시 조회된 결과값들이 최초로 담기는 공간
		
		//실행할 sql
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME = '" + pName + "'";
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		
			//3) Statemente 객체 생성
			stmt = conn.createStatement(); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.

			//4,5) sql전송 및 결과받기
			rset = stmt.executeQuery(sql);
			
			//6) ResultSet으로부터 데이터를 하나씩 꺼내서 담아준다.
			while(rset.next()) {

				p.setpName(rset.getString("product_name"));
				p.setPrice(rset.getInt("price"));
				p.setAmount(rset.getInt("amount"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}
	
	
	
	public int buyProduct(String pName) {
		//필요한 변수를 세팅
		Product p = new Product(); //비어있는 상태
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE PRODUCT SET AMOUNT = (AMOUNT-1) WHERE PRODUCT_NAME = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql); //미완성 sql문
						
			pstmt.setString(1, pName);

			result = pstmt.executeUpdate();


			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public int addProduct(String pName, int price, int amount) {
		
		int result = 0; // 처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; // 연결된 DB정보를 담는 객체
		PreparedStatement pstmt = null; // 완성된 sql문 전달해서 곧바로 실행 후 결과를 받는 객체

		String sql = "INSERT INTO PRODUCT VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?)";
		
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) Statement 객체 생성
			pstmt = conn.prepareStatement(sql); //아직 미완성 sql문으로 ?를 전부 채워줘야한다.
			pstmt.setString(1, pName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, amount);

			
			//4, 5) sql문 전달하면서 실행 후 값 받아오기
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//7) 다쓴 jdbc객체를 반환
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	
	public int deleteProduct(String pName) {
		//delete문 => 처리된 행 수 
		int result = 0;
		
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_NAME = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql); //미완성 sql문
			pstmt.setString(1, pName);

			result = pstmt.executeUpdate();
			
			if(result == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}


