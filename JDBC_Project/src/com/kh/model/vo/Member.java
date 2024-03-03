package com.kh.model.vo;

import java.sql.Date;
/*
 * VO(Value Object)
 * 한명의 회원(DB테이블의 한 행의 데이터)에 대한 데이터를 기록할 수 있는 저장용 객체 
 */


public class Member {
	
	//필드는 기본적으로 db컬럼명과 유사하게 작업
	private int userNo; //번호
	private String userID; //id
	private String userPW; //pw
	private String userName; //이름
	private String gender; //성별
	private int age; //나이
	private String email; //이메일
	private String phone; //전화번호
	private String address; //주소
	private String hobby; //취미
	private Date enrollDate; //가입일
	
	
	public Member() {
		super();
	}



	public Member(String userID, String userPW, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userID = userID;
		this.userPW = userPW;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}


	public Member(int userNo, String userID, String userPW, String userName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userID = userID;
		this.userPW = userPW;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserPW() {
		return userPW;
	}


	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	@Override
	public String toString() {
		return "userNo=" + userNo + ", userID=" + userID + ", userPW=" + userPW + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", hobby=" + hobby + ", enrollDate=" + enrollDate;
	}
	
}
