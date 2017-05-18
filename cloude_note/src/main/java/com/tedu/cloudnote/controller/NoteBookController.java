package com.tedu.cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteBookService;
import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记本控制器
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/notebook")
public class NoteBookController {
	
	
	@Resource(name="notebookservice")
	private NoteBookService notebookservice;
	
	
	/**
	 * 根据用户id加载用户的笔记本
	 * @param user_id 用户id
	 * @return JSON
	 */
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult loadUserNoteBooks(String user_id){
		System.out.println("/loadbooks.do");
		NoteResult result=
				notebookservice.loadUserNoteBooks(user_id);
		System.out.println(result);
		return result;	
	}
	
	/**
	 * 添加笔记本
	 * @param noteBookName 笔记本名字
	 * @param user_id 用户id
	 * @return
	 */
	@RequestMapping("/addnotebook.do")
	@ResponseBody
	public NoteResult addNoteBook(String noteBookName, String user_id){
		NoteResult result=notebookservice.addNoteBook(noteBookName, user_id);
		return result;	
	}
}
