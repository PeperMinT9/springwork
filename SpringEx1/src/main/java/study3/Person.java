package study3;

public class Person {
	MyShop myShop;
	String name;
	String address;
	
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public void setMyShop(MyShop myShop) {
		this.myShop = myShop;
	}
	
	public void write() {
		System.out.println("** 상품 구입 정보 **");
		myShop.showShop();
		
		System.out.println("이름: " + name);
		System.out.println("주소: " + address);
	}
}
