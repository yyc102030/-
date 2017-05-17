package com.tedu.cloudnote.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteException;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("userservice")
public class UserServiceImp implements UserService{

	@Resource(name="userdao")
	private UserDao userDao;

	public NoteResult checkLogin(String name, String password) {
		NoteResult result=new NoteResult();
		User user=userDao.findUserByName(name);
		System.out.println(user);
		System.out.println("checkLogin-service");
		//用户名为空
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		try {//验证密码
			//先加密
			System.out.println("password:"+password);
			String md5_pwd=NoteUtil.md5(password);
			System.out.println("md5_pwd:"+md5_pwd);
			System.out.println("user.getCn_user_password():"+user.getCn_user_password());
			//密码不正确
			if(!md5_pwd.equals(user.getCn_user_password())){
				result.setStatus(2);
				result.setMsg("密码不正确");
				System.out.println(result);
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("登录失败",e);
		}
		//登陆成功
		result.setStatus(0);
		result.setMsg("登陆成功");
		result.setData(user);
		System.out.println("return result");
		return result;
	}

	public NoteResult regist(String name, String nick, String password) {
		NoteResult result=new NoteResult();
		User user=userDao.findUserByName(name);
		System.out.println("user:"+user);
		if(user!=null){//用户名存在
			result.setStatus(1);
			result.setMsg("用户名已经注册");
			return result;
		}else{
			try {
				user=new User();
				user.setCn_user_id(String.valueOf(System.currentTimeMillis()));
				user.setCn_user_name(name);
				user.setCn_user_nick(nick);
				String md5_pwd=NoteUtil.md5(password);
				user.setCn_user_password(md5_pwd);
			} catch (NoSuchAlgorithmException e) {
				throw new NoteException("系统错误",e);
			}
			int i=userDao.saveUser(user);
			if(i==1){
				result.setMsg("注册成功");
				result.setStatus(0);
				result.setData(user);
			}
			return result;
		}
		
	}

}
