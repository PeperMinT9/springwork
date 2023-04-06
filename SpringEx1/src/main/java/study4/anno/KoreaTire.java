package study4.anno;

import org.springframework.stereotype.Component;

@Component
public class KoreaTire implements Tire {

	@Override
	public String getTirename() {
		// TODO Auto-generated method stub
		return "한국타이어";
	}

}
