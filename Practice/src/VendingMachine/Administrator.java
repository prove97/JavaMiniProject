package VendingMachine;

public class Administrator {
	private String password;
	
	public Administrator() {
		password = "123456";
	}

	public String getPassword() { // 나중에 비밀번호를 직접적으로 알 수 없고 모를경우 수정만 가능하도록 설정
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
