package spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpring {

	@Resource
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(app.getBean("datasource")); 
		List<String> list = returnList();
		for (String string : list) {
			System.out.println(string);
		}
	}

	private static List<String> returnList() {
		int i = (int) (Math.random()*100);
		System.out.println(i);
		if(i>3){
			return new ArrayList<String>();
		}
		return null;
	}
}
