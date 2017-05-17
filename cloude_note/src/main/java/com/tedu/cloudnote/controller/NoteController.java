package com.tedu.cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记控制器
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource(name="noteservice")
	private NoteService noteservice;
	
	/**
	 * 查询每一个笔记本的所有笔记
	 * @return JSON
	 */
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult loadNotes(String noteBookId){
		NoteResult result=
				noteservice.findNotes(noteBookId);
		return result;		
	}
	
	/**
	 * 处理查询笔记内容的请求
	 * @param noteId
	 * @return JSON
	 */
	@RequestMapping("/loadNoteBody.do")
	@ResponseBody
	public NoteResult loadNoteBody(String noteId){
		NoteResult result=
				noteservice.findNoteBody(noteId);
		return result;	
	}
	
	/**
	 * 保存笔记控制器
	 * @param noteId 笔记id
	 * @param noteTitle 笔记标题
	 * @param noteBody 笔记内容
	 * @return JSON
	 */
	@RequestMapping("/saveNote.do")
	@ResponseBody
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody){
		NoteResult result=noteservice.saveNote(noteId, noteTitle, noteBody);
		return result;	
	}
}
