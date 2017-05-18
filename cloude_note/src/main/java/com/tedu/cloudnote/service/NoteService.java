package com.tedu.cloudnote.service;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记的持久层
 * @author lenovo
 *
 */
public interface NoteService {
	/**
	 * 根据笔记本id查询所有的笔记
	 * @param notebook_id
	 * @return JSON
	 */
	public NoteResult findNotes(String notebook_id);
	
	/**
	 * 根据笔记的id查询笔记的内容
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult findNoteBody(String noteId);
	
	/**
	 * 保存笔记
	 * @param noteId 笔记的id
	 * @param noteTitle 要保存笔记的标题
	 * @param noteBody 要保存笔记的内容
	 * @return JSON
	 * 
	 */
	public NoteResult saveNote(String noteId,String noteTitle,String noteBody);
	
	/**
	 * 添加笔记
	 * @param noteBookId
	 * @param noteId
	 * @param noteTitle
	 * @param userId
	 * @return JSON
	 */
	public NoteResult addNote(String noteBookId,String noteTitle,String userId);
}
