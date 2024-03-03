package com.kh.view;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductManagement;
import com.kh.model.vo.Admin;
import com.kh.model.vo.Product;

public class MainView {
	Scanner sc = new Scanner(System.in);
	ProductManagement pm = new ProductManagement();
	
	public void mainMenu() {
		int selectMenu = 0;
		while(true) {
			System.out.println("===== 음료 자판기 =====");
			System.out.println("1. 현금 계산");
			System.out.println("2. 카드 계산");
			System.out.println("8. 관리자 메뉴");
			System.out.println("9. 종료");
			System.out.print("메뉴를 입력하세요 : ");
			selectMenu = sc.nextInt();
			sc.nextLine();
			
			switch(selectMenu) {
			case 1:
				this.payCashMenu();
				break;
			
			case 2:
				this.payCardMenu();
				break;

			case 8:
				this.printAdminMenu();
				break;
				
			case 9:
				System.out.println("종료합니다.");
				sc.close();
				return;
			}
			
		}
	}
	
	public void payCashMenu() { // 현금결제 메뉴창 출력
		System.out.print("현금을 투입해 주세요 : ");
		int cash = sc.nextInt();
		sc.nextLine();
		
		boolean isHigher = false;
		while(!isHigher) {
			for(Product p : pm.printProductList()) { 
				if(p.getPrice() <= cash) { //각 모든 상품들의 가격과 비교, 투입 금액이 더 크다면 반복문 탈출
					isHigher = true;
					break;
				}		
			}
			if(!isHigher) {
				System.out.print("금액이 모자랍니다. 추가로 투입해 주세요(현재 "+ cash + "원) : "); // 투입금액이 모든 상품의 가격보다 낮다면 추가로 금액을 입력받는다.
				cash += sc.nextInt();
				sc.nextLine();
			}
		}
		
		int i = 1;
		for(Product p : pm.printProductList()) {
			if(p.getAmount() == 0 || p.getPrice() > cash) { //투입금액보다 낮거나 재고가 없을 경우 구매불가
				System.out.printf("%d) %s(%d원)(구매불가)\n", i++, p.getpName(), p.getPrice());
			} else if(p.getAmount() != 0) {
				System.out.printf("%d) %s(%d원)\n", i++, p.getpName(), p.getPrice());
			}
			
		}
		
		String pName;
		int buyP;
		while(true) {
			System.out.print("구매할 상품(상품명 입력) : ");
			pName = sc.nextLine();
			
			buyP = pm.buyProduct(pName);
			if(buyP == 0) {
				System.out.println("구매 실패하였습니다. 다시 시도해주세요");
				continue;
			}
			
			break;
		}
		
		System.out.println("구매 완료!");
		System.out.println("거스름돈 : " + (cash - pm.printProduct(pName).getPrice()));
		System.out.println();
		
	}
	
	
	public void payCardMenu() { // 카드결제 메뉴창 출력
		System.out.println("아직 미구현 상태");
	}
	
	
	
	
	//---------------관리자--------------------

	public void printAdminMenu() { // 관리자 메뉴창 출력
		String inputPassword; 
		int inputCnt = 0;
		
		while(true) {
			if(inputCnt == 5) { // 5번 틀릴 경우 메인메뉴로 돌아감
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}

			System.out.print("비밀번호를 입력하세요 : ");
			inputPassword = sc.next(); //비밀번호 입력 받음
			
			if(inputPassword.equals(new Admin().getPassword())) {
				System.out.println("환영합니다!");
				break;
			}
			
			inputCnt++;
			if(inputCnt < 5) {
				System.out.println("일치하지 않습니다. 다시 입력하세요(틀린횟수 : " + inputCnt + "/5)");
			} 
		}
		
		int selectMenu = 0;
		while(true) {
			System.out.println("===== 관리자 메뉴 =====");
			System.out.println("1. 재고 확인");
			System.out.println("2. 상품 관리");
			System.out.println("9. 메인메뉴로");
			System.out.print("메뉴를 입력하세요 : ");
			selectMenu = sc.nextInt();
			sc.nextLine();
			
			switch(selectMenu) {
			case 1:
				this.printProductInfo();
				break;
			
			case 2:
				this.productManagerMenu();
				break;
				
			case 9:
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}
		}
	}
	
	public void printProductInfo() { // 상품 정보 출력
		ArrayList<Product> productList = pm.printProductList();
		for(Product p : productList) {
			System.out.println(p);
		}
	}
	
	public void productManagerMenu() {
		int selectMenu = 0;
		while(true) {
			System.out.println("===== 상품 관리 =====");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 삭제");			
			System.out.println("3. 상품 정보 변경");
			System.out.println("9. 관리자 메인메뉴로 돌아갑니다");
			System.out.print("메뉴를 입력하세요 : ");
			selectMenu = sc.nextInt();
			sc.nextLine();
			
			switch(selectMenu) {
			
			case 1:
				this.addProduct();
				break;
			
			case 2:
				this.deleteProduct();
				break;
				
			case 3:
				this.productInfoUpdateMenu();
				break;
			
			case 9:
				System.out.println("관리자 메뉴로 돌아갑니다.");
				return;
			}
			
		}
	}
	
	
	public void addProduct() { // 새로운 상품 추가
		System.out.print("추가할 상품명 : ");
		String name = sc.nextLine();
		System.out.print("가격 설정: ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		if(pm.addProduct(name, price, amount) == 1) {
			System.out.println("신제품 추가 완료!");
			return;
		}
		
		System.out.println("신제품 추가 실패");
		
	}

	public void deleteProduct() { //선택한 상품 삭제
		System.out.print("삭제할 상품명 : ");
		String name = sc.nextLine();
		
		if(pm.deleteProduct(name) == 1) {
			System.out.println("상품 삭제 완료!");
			return;
		}
		System.out.println("삭제 실패");
		
	}
	
	
	public void productInfoUpdateMenu() {
		int selectMenu = 0;

		System.out.println("===== 상품 관리 =====");
		System.out.println("1. 상품명 변경");
		System.out.println("2. 가격 변경");
		System.out.println("3. 수량 변경");
		System.out.print("메뉴를 입력하세요 : ");
		selectMenu = sc.nextInt();
		sc.nextLine();
		
		switch(selectMenu) {
		case 1:
			this.pNameUpdate(); // 상품명 변경
			break;
		
		case 2:
			this.priceUpdate();
			break;

		case 3:
			this.amountUpdate();
			break;
		}
	}
	
	public void pNameUpdate() {
		
	}
	public void priceUpdate() {
		
	}
	public void amountUpdate() {
		
	}
	
	
	
}

