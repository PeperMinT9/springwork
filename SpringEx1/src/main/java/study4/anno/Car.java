package study4.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	// Tire tire;
	
	// 같은 타입을 찾아서 자동 주입
	@Autowired
	KoreaTire tire;
	
	public void myCarTire() {
		System.out.println("내 차의 타이어는 " + tire.getTirename());
	}
}
