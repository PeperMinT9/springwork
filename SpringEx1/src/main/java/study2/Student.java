package study2;

public class Student {
	String schoolName;
	
	MyInfo myInfo;
	
	public Student(MyInfo myInfo) {
		// TODO Auto-generated constructor stub
		this.myInfo = myInfo;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public void show() {
		System.out.println(myInfo);
		System.out.println("ÇÐ±³¸í " + schoolName);
	}
}
