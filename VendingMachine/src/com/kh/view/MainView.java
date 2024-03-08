package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.CreditcardManagement;
import com.kh.controller.ProductManagement;
import com.kh.model.vo.Admin;
import com.kh.model.vo.Creditcard;
import com.kh.model.vo.Customer;
import com.kh.model.vo.Product;

public class MainView {
	Scanner sc = new Scanner(System.in);
	ProductManagement pm = new ProductManagement();
	CreditcardManagement cm = new CreditcardManagement();
	Admin admin = new Admin();
	Customer cust = new Customer();
	
	public void mainMenu() {
		int selectMenu = 0;
		while(true) {
			System.out.println();
			System.out.println("===== 음료 자판기 =====");
			System.out.println("1. 현금 계산");
			System.out.println("2. 카드 계산");
			System.out.println("9. 관리자 메뉴");
			System.out.println("0. 종료");
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

			case 9:
				this.printAdminMenu();
				break;
				
			case 0:
				System.out.println("종료합니다.");
				sc.close();
				return;
				
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
	}
	
	public void payCashMenu() { // 현금결제 메뉴창 출력
		int i = 1;
		int result;
		System.out.println("===== 상품 =====");
		for(Product p : pm.selectList()) {
			System.out.printf("%d) %s(%d원)\n", i++, p.getpName(), p.getPrice());
			if(i == pm.selectList().size()) {
				i = 1; //list크기만큼 반복이 끝나면 i값 1로 초기화
			}
		}
		
		int cash;
		System.out.println();
		System.out.print("현금을 투입해 주세요 : ");
		while(true) {
			cash = sc.nextInt();
			sc.nextLine();
			
			result = pm.insertCash(cash);
			if(result == 0) {
				continue;
			} else break;
		}
		
		String pName = sc.nextLine();
		pm.buyProduct(pName);
		
		
		
		
		
//		int i = 1;
//		int result;
//		System.out.println("===== 상품 =====");
//		for(Product p : pm.selectList()) {
//			System.out.printf("%d) %s(%d원)\n", i++, p.getpName(), p.getPrice());
//		}
//		
//		int cash;
//		System.out.println();
//		System.out.print("현금을 투입해 주세요 : ");
//		while(true) {
//			cash = sc.nextInt();
//			sc.nextLine();
//			
//			result = pm.insertCash(cash);
//			if(result == 0) {
//				
//			}
//			break;
//		}
//		
//		
//		
//		System.out.println();
//		i = 1;
//		for(Product p : pm.selectList()) {
//			if(p.getAmount() == 0 || p.getPrice() > cust.getCash()) { //투입금액보다 낮거나 재고가 없을 경우 구매불가
//				System.out.printf("%d) %s(%d원)(구매불가)\n", i++, p.getpName(), p.getPrice());
//			} else if(p.getAmount() != 0) {
//				System.out.printf("%d) %s(%d원)\n", i++, p.getpName(), p.getPrice());
//			}
//			
//		}
//		
//		String pName;
//		int buyP;
//		while(true) {
//			System.out.println();
//			System.out.println("현재금액 : " + cust.getCash() + "원");
//			System.out.print("구매할 상품(상품명 입력) : ");
//			pName = sc.nextLine();
//			
//			buyP = pm.buyProduct(pName);
//			
//			if(buyP == 0) {
//				System.out.println("구매 실패하였습니다. 다시 시도해주세요");
//				pm.rollbackProductAmount(pName);
//				continue;
//			}
//			
//			if(pm.selectProduct(pName).getAmount() <= 0){// 재고가 없을 시
//				System.out.println("재고가 없습니다. 다시 입력해주세요");
//				pm.rollbackProductAmount(pName);
//				continue;
//			}
//			
//			if(pm.selectProduct(pName).getPrice() > cust.getCash()) { //투입 금액보다 상품의 금액이 더 크다면
//				System.out.println("돈이 부족합니다. 다시 입력해주세요.");
//				pm.rollbackProductAmount(pName);
//				continue;
//			}		
//
//			break;
//		}
//		
//		System.out.println("구매 완료!");
//		admin.setIncome(admin.getIncome() + pm.selectProduct(pName).getPrice()); //수익 더함
//		System.out.println("거스름돈 : " + (cust.getCash() - pm.selectProduct(pName).getPrice()) + "원");
		
	}
	
	
	public void payCardMenu() { // 카드결제 메뉴창 출력
		for(Creditcard c : cm.cardList()) {
			System.out.println(c);
		}
		System.out.println();
		System.out.print("결제 카드 선택 : ");
		
	}
	
	
	
	
	//---------------관리자--------------------

	public void printAdminMenu() { // 관리자 메뉴창 출력
		if(!this.accessAdmin()) {
			return;
		}
		System.out.println();
		System.out.println("환영합니다!");
		int selectMenu = 0;
		while(true) {
			System.out.println();
			System.out.println("===== 관리자 메뉴 =====");
			System.out.println("1. 재고 확인");
			System.out.println("2. 수익 확인");
			System.out.println("3. 상품 관리");
			System.out.println("9. 비밀번호 변경");
			System.out.println("0. 메인메뉴로");
			System.out.print("메뉴를 입력하세요 : ");
			selectMenu = sc.nextInt();
			sc.nextLine();
			
			switch(selectMenu) {
			case 1:
				this.printProductInfo();
				break;
			
			case 2:
				System.out.println("현재 수익은 " + admin.getIncome() + "원 입니다.");
				break;
				
			case 3:
				this.productManagerMenu();
				break;
				
			case 9:
				if(this.accessAdmin()) {
					this.updatePassword();
				}
				break;

			case 0:
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}
		}
	}
	
	/**
	 * 관리자모드 접속을 위한 비밀번호 비교 메소드
	 * @return 비밀번호가 일치하면 true, 5번 동안 일치하지 않으면 false 반환 
	 */
	public boolean accessAdmin() {
		String inputPassword;
		int inputCnt = 0;
		
		while(true) {
			if(inputCnt == 5) { // 5번 틀릴 경우 메인메뉴로 돌아감
				System.out.println("메인메뉴로 돌아갑니다.");
				return false;
			}
			
			System.out.println();
			System.out.print("비밀번호를 입력하세요(5자리) : ");
			inputPassword = sc.nextLine(); //비밀번호 입력 받음
			
			if(inputPassword.equals(admin.getPassword())) {
				return true;
			}
			
			inputCnt++;
			if(inputCnt < 5) {
				System.out.println("일치하지 않습니다. 다시 입력하세요(틀린횟수 : " + inputCnt + "/5)");
			} 
		}
	}
	
	public void printProductInfo() { // 상품 정보 출력
		ArrayList<Product> productList = pm.selectList();
		for(Product p : productList) {
			System.out.println(p);
		}
	}
	
	/**
	 * 비밀번호 변경 메소드: 입력받은 문자열이 5글자가 아니면 다시 입력받는다.
	 */
	public void updatePassword() {
		String inputPassword = null; 
		do {
			if(inputPassword != null) {
				System.out.println("잘못된 입력입니다.");
			}
			System.out.print("변경할 비밀번호를 입력해주세요(5자리) : ");
			inputPassword = sc.nextLine();
		} while(inputPassword.length() != 5);
		System.out.println("변경 완료!");
		admin.setPassword(inputPassword);
	}
	
	public void productManagerMenu() {
		int selectMenu = 0;
		while(true) {
			System.out.println();
			System.out.println("===== 상품 관리 =====");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 삭제");			
			System.out.println("3. 상품 재고 보충");
			System.out.println("4. 상품 가격 변경");
			System.out.println("0. 관리자 메인메뉴로 돌아갑니다");
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
				this.productRefill();
				break;

			case 4:
				this.priceUpdate();
				break;
			
			case 0:
				System.out.println("관리자 메뉴로 돌아갑니다.");
				return;
			}
			
		}
	}
	
	/**
	 * 새로운 상품 추가 메소드
	 */
	public void addProduct() { 
		System.out.println();
		System.out.print("추가할 상품명 : ");
		String pName = sc.nextLine();
		System.out.print("가격 설정: ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();

		int result = pm.addProduct(pName, price, amount);
		
		if(result > 0) {
			System.out.println("신제품 추가 완료!");
			return;
		}
		
		System.out.println("신제품 추가 실패");
		
	}

	/**
	 * 선택한 상품 삭제 메소드
	 */
	public void deleteProduct() { 
		System.out.println();
		System.out.print("삭제할 상품명 : ");
		String pName = sc.nextLine();
		
		int result = pm.deleteProduct(pName);
		
		if(result > 0) {
			System.out.println("상품 삭제 완료!");
			return;
		}
		System.out.println("삭제 실패");
		
	}
	
	/**
	 * 상품 재고 보충하는 메소드
	 */
	public void productRefill() { 
		System.out.println();
		System.out.print("재고를 보충할 상품명을 입력하세요(\"all\"이라 입력하면 모든 상품 보충) : ");
		String pName = sc.nextLine().toLowerCase();
		System.out.print("추가할 재고 수 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		int result = 0;
		if(pName.equals("all")) {
			result = pm.productRefill(amount);
		} else {
			result = pm.productRefill(pName, amount);
		}
		
		if(result > 0) {
			System.out.println("재고 보충 완료!");
			return;
		}
		System.out.println("보충 실패. 다시 시도하세요.");
		
	}
	
	/**
	 * 선택한 상품 가격 변경 메소드
	 */
	public void priceUpdate() {
		System.out.println();
		System.out.print("가격을 변경할 상품명을 입력하세요 : ");
		String pName = sc.nextLine();
		System.out.print("변경할 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		int result = pm.priceUpdate(pName, price);
		if(result > 0) {
			System.out.println("가격 변경에 성공하였습니다.");
			return;
		} 
		System.out.println("가격 변경에 실패, 다시 시도해주세요.");
		
		
	}

	
//	----응답
	
	public void printMessage(String message) {
		System.out.println(message);
	}
}

