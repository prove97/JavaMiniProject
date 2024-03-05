package com.kh.model.vo;

public class Admin extends Human{
	private String password; //관리자 모드 접속을 위한 비밀번호
	private int income;
	
	public Admin() {
		super();
		password = "12345";
		income = 0;
	}

	public String getPassword() { // 나중에 비밀번호를 직접적으로 알 수 없고 모를경우 수정만 가능하도록 설정
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}
	
}
