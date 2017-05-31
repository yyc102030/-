package com.tedu.cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

//登陆控制器
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name="userservice")
	private UserService userservice;
	/**
	 * 验证登录操作
	 * @param name 用户名
	 * @param password 密码
	 * @return JSON 返回数据类型
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult checkLogin(String name,String password){
		System.out.println("checkLogin");
		NoteResult result=userservice.checkLogin(name, password);
		System.out.println(result.getMsg());
		return result;
	}
	
	/**
	 * 注册控制器
	 */
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult regist(
			String name, 
			String password, 
			String nick){
			if(nick==null){
				nick=name;
			}
			NoteResult result=userservice.regist(name, nick, password);		
			return result;		
	}
	
	/**
	 * 验证旧密码
	 * @param userId
	 * @param lastPwd
	 * @return JSON
	 */
	@RequestMapping("/checklastpwd.do")
	@ResponseBody
	public NoteResult changePwd(String userId ,String lastPwd){
		NoteResult result=userservice.changePwd(userId, lastPwd);
		return result;		
	}
	
	/**
	 * 修改密码控制器
	 * @param userId
	 * @param newPwd
	 * @return JSON
	 */
	@RequestMapping("/modifypwd.do")
	@ResponseBody
	public NoteResult modifyPwd(String userId, String newPwd){
		NoteResult result=userservice.modifyPwd(userId, newPwd);
		return result;		
	}
}
