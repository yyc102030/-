package com.tedu.cloudnote.service;

import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记本列表的服务层
 * @author lenovo
 *
 */
public interface NoteBookService {

	/**
	 * 根据用户的id来查询属于他的笔记本列表
	 * @param user_id
	 * @return 笔记本列表
	 */
	public NoteResult loadUserNoteBooks(String user_id);
	
	/**
	 * 添加笔记本
	 * @param noteBookName 笔记本名字
	 * @param user_id 用户id
	 * @return
	 */
	public NoteResult addNoteBook(String noteBookName,String user_id);
}
