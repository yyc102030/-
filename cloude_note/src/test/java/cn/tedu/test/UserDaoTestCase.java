package cn.tedu.test;

import org.junit.Test;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;

public class UserDaoTestCase 
	extends BaseTestCase{
	
	@Test 
	public void testFindUserByName(){
		String name="demo";
		UserDao dao = ctx.getBean(
				"userDao", UserDao.class);
		System.out.println(dao);
		User user=dao.findUserByName(name);
		System.out.println(user);
	}
}






