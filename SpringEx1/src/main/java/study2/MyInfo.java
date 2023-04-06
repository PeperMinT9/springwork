package study2;

public class MyInfo {
	private String name;
	private int age;
	private String hobby;
	
	public MyInfo(String name, int age, String hobby) {
		super();
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "MyInfo [name=" + name + ", age=" + age + ", hobby=" + hobby + "]";
	}
	
	
}
