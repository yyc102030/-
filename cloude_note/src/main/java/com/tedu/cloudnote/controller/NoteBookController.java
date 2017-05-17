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
@RequestMapping("/book")
public class NoteBookController {
	
	@Resource(name="notebookservice")
	private NoteBookService notebookservice;
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult loadUserNoteBooks(String user_id){
		System.out.println("/loadbooks.do");
		NoteResult result=
				notebookservice.loadUserNoteBooks(user_id);
		System.out.println(result);
		return result;	
	}
}
