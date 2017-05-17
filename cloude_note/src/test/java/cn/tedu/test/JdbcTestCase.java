package cn.tedu.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;

/**
 * 测试数据库连接 
 */
public class JdbcTestCase 
	extends BaseTestCase{

	@Test
	public void testDataSource() 
			throws Exception {
		DataSource ds = ctx.getBean(
			"dataSource", DataSource.class);
		Connection conn = ds.getConnection();
		DatabaseMetaData md=conn.getMetaData();
		System.out.println(md);
		//查询数据库版本
		String n = md.getDatabaseProductName();
		String v = md.getDatabaseProductVersion();
		System.out.println(n+v); 
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		System.out.println(dao);
		dao.findUserByName("demo");
		SqlSessionFactory ssf=ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(ssf);
		conn.close();
	}
}




