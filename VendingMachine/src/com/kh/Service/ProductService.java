package com.kh.Service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDAO;
import com.kh.model.vo.Product;

public class ProductService {
	ProductDAO pdao = new ProductDAO();

	
	public ArrayList<Product> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = pdao.selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int buyProduct(String pName) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.buyProduct(conn, pName);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int rollbackProductAmount(String pName) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.rollbackProductAmount(conn, pName);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public Product selectProduct(String pName) {
		Connection conn = JDBCTemplate.getConnection();
		
		Product p = pdao.selectProduct(conn, pName);
		
		JDBCTemplate.close(conn);
		
		return p;
	}
	
	
	public int addProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.addProduct(conn, p);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteProduct(String pName) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.deleteProduct(conn, pName);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	public int productRefill(int amount) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.productRefill(conn, amount);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int productRefill(String pName, int amount) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.productRefill(conn, pName, amount);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;	
		}
	
	
	public int priceUpdate(String pName, int price) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = pdao.priceUpdate(conn, pName, price);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
}
