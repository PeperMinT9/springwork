package board.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int idx;
	private int num;
	private String subject;
	private String content;
	private String images;
	private int readcount;
	private Timestamp writeday;
	
	public int getIdx() {
		return idx;
	}
	
	public int getNum() {
		return num;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getImages() {
		return images;
	}
	
	public int getReadcount() {
		return readcount;
	}
	
	public Timestamp getWriteday() {
		return writeday;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setImages(String images) {
		this.images = images;
	}
	
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	public void setWriteday(Timestamp writeday) {
		this.writeday = writeday;
	}
}
