package VendingMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	Scanner sc = new Scanner(System.in);
	ProductManagement pm = new ProductManagement();
	Administrator admin = new Administrator();
	
	public void setCardBudget() {
		// 카드 금액 설정
	}
	
	public void printMenu() {
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
				this.print_VM_Manager();
				break;
				
			case 9:
				System.out.println("종료합니다.");
				sc.close();
				return;
			}
			
		}
	}
	
	public void print_VM_Manager() { // 관리자 메뉴창 출력
		String inputPassword; 
		int inputCnt = 0;
		
		
		while(true) {
			if(inputCnt == 5) { // 5번 틀릴 경우 메인메뉴로 돌아감
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}

			System.out.print("비밀번호를 입력하세요 : ");
			inputPassword = sc.next(); //비밀번호 입력 받음
			
			if(inputPassword.equals(admin.getPassword())) {
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
			System.out.println("2. 재고 관리");
			System.out.println("3. 상품 추가");
			System.out.println("4. 상품 삭제");
			System.out.println("9. 메인메뉴로");
			System.out.print("메뉴를 입력하세요 : ");
			selectMenu = sc.nextInt();
			sc.nextLine();
			
			switch(selectMenu) {
			case 1:
				this.printProduct();
				break;
			
			case 2:
				this.productManager();
				break;

			case 3:
				this.addProduct();
				break;
			
			case 4:
				this.deleteProduct();
				break;
				
			case 9:
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}
		}
	}
	
	public void productManager() {
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
			this.payCashMenu(); // 상품명 변경
			break;
		
		case 2:
			this.payCardMenu();
			break;

		case 3:
			this.print_VM_Manager();
			break;
		}
	}
	
	
	public void payCardMenu() { // 카드결제 메뉴창 출력
		int selectMenu = sc.nextInt();
		
		System.out.print("카드 남은 금액 : ");
	}
	
	public void payCashMenu() { // 현금결제 메뉴창 출력
		
	}
	
	public void printProduct() { // 음료 재고 출력
		ArrayList<Product> productList = pm.printProduct();
		for(Product p : productList) {
			System.out.println(p);
		}
	}
	
	public void addProduct() {
		System.out.print("추가할 상품명 : ");
		String name = sc.nextLine();
		System.out.print("가격 설정: ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		if(pm.addProduct(name, price, amount)) {
			System.out.println("신제품 추가 완료!");
			return;
		}
		
		System.out.println("신제품 추가 실패");
		
	}

	public void deleteProduct() {
		System.out.print("삭제할 상품명 : ");
		String name = sc.nextLine();
		
		if(pm.deleteProduct(name)) {
			System.out.println("상품 삭제 완료!");
			return;
		}
		System.out.println("삭제 실패");
		
	}
	
	
}

