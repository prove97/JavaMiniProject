package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.vo.Product;
import com.kh.model.dao.ProductDAO;

public class ProductManagement {
	ArrayList<Product> productList = new ArrayList<>();
	ProductDAO pdao = new ProductDAO();

	public ProductManagement() {
		super();
		productList = pdao.selectList();
		
	}

	public ArrayList<Product> printProductList() { //모든 상품 정보 출력(ArrayList로 넘기는 역할)
		return pdao.selectList();
	}
	
	public int buyProduct(String pName) {
		return pdao.buyProduct(pName);
	}
	
	public Product printProduct(String pName) {
		return pdao.selectProduct(pName);
	}

	//관리자 메소드
	public void refill() { //관리자 메뉴에서 부족한 재고 채움
		
	}
	
	public int addProduct(String pName, int price, int amount) {
		
		return pdao.addProduct(pName, price, amount);
		
//		int size = productList.size();
//		productList.add(new Product(name, price, amount));
//		
//		if(size == productList.size()) { // productList의 크기가 새상품을 추가해주기 전과 크기가 변하지 않았다면 false 반환
//			return false;
//		}
//		return true;
	}
	
	public int deleteProduct(String pName) {

		return pdao.deleteProduct(pName);
		//		Product rmProduct = null;
//		for(Product p : productList) {
//			if(p.getpName().equals(name)) {
//				rmProduct = p;
//			}
//		}
//		if(rmProduct != null && productList.remove(rmProduct)) {
//			return true;
//		}
//		
//		return false;
	}
	

	
}
