package board.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AnswerDto {
	private int seq;
	private int idx;
	private int num;
	private String content;
	private String name;
	private String photo;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp writeday;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getIdx() {
		return idx;
	}
	
	public int getNum() {
		return num;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoto() {
		return photo;
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
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public void setWriteday(Timestamp writeday) {
		this.writeday = writeday;
	}
}
