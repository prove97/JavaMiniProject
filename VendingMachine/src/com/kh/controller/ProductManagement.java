package com.kh.controller;

import java.util.ArrayList;

import com.kh.Service.ProductService;
import com.kh.model.dao.ProductDAO;
import com.kh.model.vo.Product;

public class ProductManagement {
	ArrayList<Product> productList = new ArrayList<>();
	ProductService ps = new ProductService();

	public ProductManagement() {
		super();
		productList = ps.selectList();
		
	}

	public ArrayList<Product> selectList() { //모든 상품 정보 출력(ArrayList로 넘기는 역할)
		return ps.selectList();
	}
	
	public int buyProduct(String pName) {
		return ps.buyProduct(pName);
	}
	public int rollbackProductAmount(String pName) {
		return ps.rollbackProductAmount(pName);
	}

	
	public Product selectProduct(String pName) {
		return ps.selectProduct(pName);
	}

	//----------------관리자 메소드------------------------
	
	public int addProduct(String pName, int price, int amount) {
		Product p = new Product(pName, price, amount);

		return ps.addProduct(p);
		
	}
	
	public int deleteProduct(String pName) {

		return ps.deleteProduct(pName);
		
	}
	
	public int productRefill(int amount) {
		return ps.productRefill(amount);

	}
	
	public int productRefill(String pName, int amount) { //오버로딩
		return ps.productRefill(pName, amount);

	}
	
	public int priceUpdate(String pName, int price) {
		
		return ps.priceUpdate(pName, price);
		
	}
	
	

	
}
