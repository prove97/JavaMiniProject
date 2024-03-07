package com.kh.controller;

import java.util.ArrayList;

import com.kh.Service.ProductService;
import com.kh.model.vo.Customer;
import com.kh.model.vo.Product;
import com.kh.view.MainView;

public class ProductManagement {
	ProductService ps = new ProductService();
	Product p = new Product();
	Customer cust = new Customer();
	MainView mv = new MainView();
	
	
	public void insertCash(int cash) {
		int i = 1;
		for(Product p : this.selectList()) {
			System.out.printf("%d) %s(%d원)\n", i++, p.getpName(), p.getPrice());
		}
		
		mv.printMessage("금액이 부족합니다. 추가로 투입해 주세요. (현재 " + cust.getCash() + "원) : ");
		
		boolean isHigher = false;
		while(true) {
			for(Product p : this.selectList()) { 
				if(p.getPrice() <= cust.getCash()) { //각 모든 상품들의 가격과 비교, 하나라도 투입 금액보다 더 큰게 있다면 반복문 탈출
					isHigher = true;
					break;
				}		
			}
			if(!isHigher) {
				System.out.print("금액이 모자랍니다. 추가로 투입해 주세요(현재 "+ cust.getCash() + "원) : "); // 투입금액이 모든 상품의 가격보다 낮다면 추가로 금액을 입력받는다.
				cust.setCash(cust.getCash() + sc.nextInt());
				sc.nextLine();
				continue;
			} else break;
		}
		
	}
	
	public int buyProduct(String pName) {
		return ps.buyProduct(pName);
	}
//	payCash()
	
	
	public ArrayList<Product> selectList() { //모든 상품 정보 출력(ArrayList로 넘기는 역할)
		return ps.selectList();
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
