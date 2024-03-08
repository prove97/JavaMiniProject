package com.kh.model.vo;

public class Customer{
	private int cash;
	

	public Customer() {
		super();
	}

	public Customer(int cash) {
		super();
		this.cash = cash;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "현재 금액 : " + this.cash;
	}
	
	
}
