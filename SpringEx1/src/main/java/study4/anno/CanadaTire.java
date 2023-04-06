package study4.anno;

import org.springframework.stereotype.Component;

// @Component: xml없이 자동으로 bean등록(id는 자동으로 첫문자가 소문자로 바뀌어 canadaTire)
// @Component("cTire"): id가 cTire가 된다

@Component
public class CanadaTire implements Tire {

	@Override
	public String getTirename() {
		// TODO Auto-generated method stub
		return "캐나다타이어";
	}

}
