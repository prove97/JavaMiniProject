package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	
	//Scanner 객체 생성(전역으로 다 입력받을 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	//MemberController 객체생성 (전역에서 바로 요청할 수 있도록)
	private MemberController mc = new MemberController();
	
	/*
	 * 사용자가 보게될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("=====회원관리 프로그램=====");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 정보 변경");
			System.out.println("4. 회원 탈퇴");
//			System.out.println("5. 회원 아이디 검색");
//			System.out.println("6. 회원 이름으로 키워드 겁색");
			System.out.println("0. 프로그램 종료");
		
			System.out.print("메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				this.inputMember();			
				break;
			case 2:
				mc.selectList(); //회원정보 받아옴 -> controller에 요청
				break;
			case 3:
				//userID
				//패스워드, 이메일, 전화번호, 주소
				this.updateMember();
				break;
				
			case 4:
				String userID = inputMemberID();
				mc.deleteMember(userID); 
				break;
				
			case 5:
			
			case 6:
			
			case 0:
				System.out.println("이용해주셔서 감사합니다. 프로그램을 종료합니다.");
				return;
			
			default:
				System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요.");
			}
	
		}
	}
	
	public void inputMember() {
		System.out.println("=====회원추가=====");
		//id~취미까지 입력받기 
		
//		System.out.print("아이디 : ");
//		String userID = sc.nextLine();	
		String userID = this.inputMemberID();
		
		System.out.print("비밀번호 : ");
		String userPW = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M, F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		String age = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-빼고입력) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 이어서 작성) : ");
		String hobby = sc.nextLine();
		
		//회원 추가 요청
		mc.insertMember(userID, userPW, userName, gender, age, email, phone, address, hobby);
	}
	
	public void updateMember() {
		System.out.println("\n=======회원 정보 변경=======");
		
		//(어떤 회원인지 찾기위한)아이디, 비밀번호, 이메일, 전화번호, 주소
		
		System.out.print("아이디 : ");
		String userID = sc.nextLine();

		System.out.print("변경할 비밀번호 : ");
		String userPW = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.println("변경할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userID, userPW, email, phone, address);
	}

	
	public String inputMemberID() {
		System.out.print("아이디 : ");
		String userID = sc.nextLine();	
		return userID;
	}
	
	//----------------------------응답화면----------------------------
	/**
	 * 서비스요청 처리 후 성공했을 경우 사용자가 보게될 응답화면
	 * @param : 객체별 성공메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스요청 처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param : 객체별 실패메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회서비스 요청시 조회결과가가 없을 때 사용자가 보게될 응답화면
	 * @param : 조회결과에 대한 응답메세지
	 */
	public void displayNoData(String message) {
		System.out.println("\n : " + message);
	}
	
	/**
	 * 조회서비스 요청시 결과가 여러행일 때 사용자가 보게될 응답화면
	 * @param : 조회결과에 대한 응답메세지
	 */
	public void displayMemberList(ArrayList<Member> list) {
//		for loop
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
//		for-each문
		for(Member m : list) {
			System.out.println(m);
		}
	}
}
