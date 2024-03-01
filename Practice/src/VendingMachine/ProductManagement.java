package VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
	ArrayList<Product> productList = new ArrayList<>();
	
	public ProductManagement() {
		super();
		productList.add(new Product("코카콜라", 2000, 10)); 
		productList.add(new Product("펩시", 1500, 10)); 
		productList.add(new Product("제주삼다수", 500, 10)); 
	}


	public ArrayList<Product> printProduct() {
		return productList;
	}
	
	
	public void refill() { //관리자 메뉴에서 부족한 재고 채움
		
	}
	
	public boolean addProduct(String name, int price, int amount) {
		int size = productList.size();
		productList.add(new Product(name, price, amount));
		
		if(size == productList.size()) { // productList의 크기가 새상품을 추가해주기 전과 크기가 변하지 않았다면 false 반환
			return false;
		}

		return true;
	}
	
	public boolean deleteProduct(String name) {
		Product rmProduct = null;
		for(Product p : productList) {
			if(p.getpName().equals(name)) {
				rmProduct = p;
			}
		}
		
		if(rmProduct != null && productList.remove(rmProduct)) {
			return true;
		}
		
		return false;
	}
	
	//오버로딩 개념 사용
	public boolean changeInfo(String name) {
		return false;
	}
	public boolean changeInfo(int price) {
		return false;
	}
	
}
