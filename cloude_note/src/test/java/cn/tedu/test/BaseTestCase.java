package cn.tedu.test;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestCase {
	protected ClassPathXmlApplicationContext ctx;
	//��ʼ�� Spring����
	@Before 
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
				"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml"
				);
	}
}




