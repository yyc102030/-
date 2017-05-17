package com.tedu.cloudnote.service;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;


public interface UserService {
	/**
	 * 验证登录
	 * @param name
	 * @param password
	 * @return json数据
	 */
	public NoteResult checkLogin(String name,String password);
	
	public NoteResult regist(String name, String nick,
			String password);
}
