package com.kh.model.vo;

public class Creditcard {
	private int cardID;
	private String cardNumber;
	private String bank;
	private int money;
	private String cardNickName;
	
	public Creditcard() {
		super();
	}

	public Creditcard(int cardID, String cardNumber, String bank, int money, String cardNickName) {
		super();
		this.cardID = cardID;
		this.cardNumber = cardNumber;
		this.bank = bank;
		this.money = money;
		this.cardNickName = cardNickName;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getCardNickName() {
		return cardNickName;
	}

	public void setCardNickName(String cardNickName) {
		this.cardNickName = cardNickName;
	}

	@Override
	public String toString() {
		return "Creditcard [cardID=" + cardID + ", cardNumber=" + cardNumber + ", bank=" + bank + ", money=" + money
				+ ", cardNickName=" + cardNickName + "]";
	}
	
	
	
}
