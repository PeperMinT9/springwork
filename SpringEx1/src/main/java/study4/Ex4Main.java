package study4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex4Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		Car car1 = (Car)context.getBean("car1");
		car1.myCarTire();
		
		Car car2 = (Car)context.getBean("car2");
		car2.myCarTire();
	}

}
