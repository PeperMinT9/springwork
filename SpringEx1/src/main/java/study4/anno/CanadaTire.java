package study4.anno;

import org.springframework.stereotype.Component;

// @Component: xml���� �ڵ����� bean���(id�� �ڵ����� ù���ڰ� �ҹ��ڷ� �ٲ�� canadaTire)
// @Component("cTire"): id�� cTire�� �ȴ�

@Component
public class CanadaTire implements Tire {

	@Override
	public String getTirename() {
		// TODO Auto-generated method stub
		return "ĳ����Ÿ�̾�";
	}

}
