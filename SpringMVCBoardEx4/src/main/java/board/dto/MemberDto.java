package board.dto;

import java.sql.Timestamp;

public class MemberDto {
	private int num;
	private String name;
	private String photo;
	private String email;
	private String pass;
	private String hp;
	private Timestamp gaipday;
	
	public int getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getHp() {
		return hp;
	}
	
	public Timestamp getGaipday() {
		return gaipday;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public void setGuipday(Timestamp gaipday) {
		this.gaipday = gaipday;
	}
}
