package cn.tedu.test;

import org.junit.Test;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

public class UserServiceTest extends BaseTestCase{
	
	@Test
	public void testUserService(){
		UserService service=ctx.getBean("userservice",UserService.class);
		NoteResult result=service.checkLogin("demo", "1234");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
}
