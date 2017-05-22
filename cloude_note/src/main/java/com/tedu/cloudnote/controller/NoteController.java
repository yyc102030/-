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
	
	/**
	 * 添加笔记控制器
	 * @param noteBookId 笔记本id
	 * @param noteId 笔记id
	 * @param noteTitle 笔记标题
	 * @param userId 用户id
	 * @return JSON 返回JSON数据类型
	 */
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult addNote(String noteBookId, String noteTitle, String userId){
		NoteResult result=noteservice.addNote(noteBookId, noteTitle, userId);
		return result;	
	};
	
	/**
	 * 
	 * 将笔记删除到回收站
	 * @param noteId 笔记Id
	 * @return JSON
	 */
	@RequestMapping("/rallbacknote.do")
	@ResponseBody
	public NoteResult rallBackNote(String noteId){
		System.out.println("11111111111111");
		NoteResult result=noteservice.rallBackNote(noteId);
		return result;	
	}
	
	/**
	 * 删除笔记控制器
	 * @param noteId
	 * @return JSON数据
	 */
	@RequestMapping("/deletenote.do")
	@ResponseBody
	public NoteResult deleteNote(String noteId){
		NoteResult result=noteservice.deleteNote(noteId);
		return result;		
	}
}
