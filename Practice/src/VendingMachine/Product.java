package VendingMachine;

public class Product {
	private String pName; // 제품명
	private int price; // 금액
	private int amount; // 남은 개수
	private boolean lightOn; // 점등 유무(재고가 있을 시)
	
	
	public Product() {
		super();
	}

	public Product(String pName, int price, int amount) {
		super();
		this.pName = pName;
		this.price = price;
		this.amount = amount;
		this.lightOn = false;
	}
	
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Product(int price) {
		super();
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isLightOn() {
		return lightOn;
	}

	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}

	@Override
	public String toString() {
		return "음료명 : " + pName + "\t가격 : " + price + "원\t수량 : " + amount;
	}
	
	
	
	
	
}


