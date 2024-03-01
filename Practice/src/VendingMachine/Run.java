package VendingMachine;

public class Run {

	public static void main(String[] args) {
		
//		new MainMenu().setCardBudget();
		new MainMenu().printMenu();
//		new ProductManagement().printBeverage();
//		new MainMenu().print_VM_Manager();
	}

}

/*
 * 자판기
 * 기본 재고 5개
 * 메인 메뉴
 * 1. 카드 계산일 경우
 * - 카드에 들어있는 금액 확인
 *   금액보다 적은 가격의 상품들 점등
 *   if 뽑을 수 있는 상품이 없으면 메인메뉴로 돌아감
 *   else 뽑을 수 있는 상품들 점등 (index 값과 함께 상품 이름 출력)
 *   
 *   선택하면 가격만큼 카드 금액 차감
 *   뽑은 상품 재고 -1 
 *   
 * 
 * 2. 현금 계산일 경우
 * - 넣을 금액 입력
 *   넣은 금액보다 작은 가격의 상품들 점등
 *   if 뽑을 수 있는 상품이 없으면 메인메뉴로 돌아감
 * 
 * 
 * 8. 관리자 메뉴
 * - 자판기에 있는 음료 재고 확인
 * - 새로운 음료 추가
 * - 음료 채우기 
 * - 현금 확인
 * - 현금 빼내기
 * 
 * 9. 종료
 * 
 * 
 * 고객 객체
 * - 카드
 *   카드에 있는 금액
 * - 현금
 *   넣은 금액
 *   
 * 음료 객체
 * - 제품명(String)
 * - 남은 재고(int)
 * - 가격(int)
 * - 버튼 점등 유무(boolean)
 * - 유통기한(Date or String) : 처음은 모두 25.1.1.로 통일해주고 
 * 
 * 
 */
