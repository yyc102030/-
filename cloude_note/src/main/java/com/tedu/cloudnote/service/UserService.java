package com.tedu.cloudnote.service;

import com.tedu.cloudnote.util.NoteResult;


public interface UserService {
	/**
	 * 验证登录
	 * @param name
	 * @param password
	 * @return json数据
	 */
	public NoteResult checkLogin(String name,String password);
	
	/**
	 * 注册功能
	 * @param name 姓名
	 * @param nick 昵称
	 * @param password 密码
	 * @return JSON
	 */
	public NoteResult regist(String name, String nick,
			String password);
	
	/**
	 * 验证密码，根据userId查询
	 * @param userId
	 * @return json
	 */
	public NoteResult changePwd(String userId,String lastPwd);
	
	/**
	 * 修改密码
	 * @param userId
	 * @param newPwd
	 * @return JSON
	 */
	public NoteResult modifyPwd(String userId,String newPwd);
}
