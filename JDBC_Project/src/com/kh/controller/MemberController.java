package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

//Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
//             해당 메소드로 전달된 데이터[가공처리후] DAO로 전달하며 호출
//             DAO로부터 반환받은 결과, 성공인지 실패인지 판단 후 응답화면 결정(view 메소드 호출)
public class MemberController {
	/*
	 * 사용자의 추가요청을 처리해주는 메소드
	 * @param userID ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userID, String userPW, String userName, String gender, 
			String age, String email, String phone, String address, String hobby) {
		
		// view로부터 전달받은 값을 바로 DAO쪽으로 전달 x
		// 어딘가(Member객체(vo))에 담아서 전달
		
		//방법1) 기본생성자로 생성 후 각 필드마다 setter를 이용해서 데이터 저장
		//방법2) 매개변수 있는 생성자로 전부 전달해서 생성		
		
		Member m = new Member(userID, userPW, userName, gender, 
				Integer.parseInt(age), email, phone, address, hobby);
		
		int result = new MemberDAO().insertMember(m);
				
		if(result > 0) {
			//성공화면
			new MemberMenu().displaySuccess("성공적으로 회원이 추가되었습니다.");
		} else {
			//실패화면
			new MemberMenu().displayFail("회원 추가를 실패하였습니다.");
		}
		
	}
	
	/**
	 * 사용자 모두를 조회하는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDAO().selectList();
		
		//조회된 결과에 따라서 사용자가 보게될 응답화면 지정
		if(list.isEmpty()) { //list가 비어있는 경우
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		} else { //list에 조회된 데이터가 있을 경우
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void updateMember(String userID, String userPW, String email, String phone, String address) {
		Member m = new Member();
		m.setUserID(userID);
		m.setUserPW(userPW);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberDAO().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보 변경되었습니다.");			
		} else {
			new MemberMenu().displayFail("회원정보 변경에 실패하였습니다.");
		}
	}
	
	public void deleteMember(String userID) {
		int result = new MemberDAO().deleteMember(userID);
		if(result == 1) {
			new MemberMenu().displaySuccess("성공적으로 삭제되었습니다.");
		} else {
			new MemberMenu().displayFail("회원정보 삭제에 실패했습니다.");			
		}
		
	}
}
