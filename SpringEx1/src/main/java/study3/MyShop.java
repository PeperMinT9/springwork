package study3;

public class MyShop {
	private String sangpum;
	private int su;
	private int dan;
	
	public MyShop(String sangpum) {
		this.sangpum = sangpum;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}
	
	public void showShop() {
		System.out.println("상품명: " + sangpum);
		System.out.println("수량: " + su);
		System.out.println("단가: " + dan);
	}
}
